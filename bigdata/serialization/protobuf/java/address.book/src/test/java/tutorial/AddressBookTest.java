package tutorial;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AddressBookTest {

    @Test
    public void test() throws IOException {

        byte[] data = null;
        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {

            AddressBookProtos.Person person =
                    AddressBookProtos.Person.newBuilder()
                            .setId(1234)
                            .setName("John Doe")
                            .setEmail("jdoe@example.com")
                            .addPhones(
                                    AddressBookProtos.Person.PhoneNumber.newBuilder()
                                            .setNumber("555-4321")
                                            .setType(AddressBookProtos.Person.PhoneType.HOME))
                            .build();

            AddressBookProtos.AddressBook addressBook =
                    AddressBookProtos.AddressBook.newBuilder()
                            .addPeople(person)
                            .build();

            addressBook.writeTo(os);
            data = os.toByteArray();
        }


        try (ByteArrayInputStream is = new ByteArrayInputStream(data)) {
            AddressBookProtos.AddressBook addressBook =
                    AddressBookProtos.AddressBook.newBuilder()
                            .mergeFrom(is)
                            .build();


            assertNotNull(addressBook);
            assertNotNull(addressBook.getPeopleList());
            assertEquals(1, addressBook.getPeopleList().size());

            AddressBookProtos.Person person = addressBook.getPeople(0);
            assertEquals("John Doe", person.getName());
            assertEquals("jdoe@example.com", person.getEmail());
            assertNotNull(person.getPhonesList());
            assertEquals(1, person.getPhonesList().size());

            AddressBookProtos.Person.PhoneNumber phoneNumber = person.getPhonesList().get(0);
            assertEquals("555-4321", phoneNumber.getNumber());
            assertEquals(AddressBookProtos.Person.PhoneType.HOME, phoneNumber.getType());
        }
    }
}
