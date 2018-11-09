package tutorial;

public class AddressBookSample {
    public static void main(String[] args) {

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

        System.out.printf("person:\n%s\n", person);
    }
}
