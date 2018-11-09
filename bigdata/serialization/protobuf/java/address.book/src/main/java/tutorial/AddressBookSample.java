package tutorial;

public class AddressBookSample {
    public static void main(String[] args) {

        Person person =
                Person.newBuilder()
                        .setId(1234)
                        .setName("John Doe")
                        .setEmail("jdoe@example.com")
                        .addPhones(
                                Person.PhoneNumber.newBuilder()
                                        .setNumber("555-4321")
                                        .setType(Person.PhoneType.HOME))
                        .build();

        System.out.printf("person:\n%s\n", person);
    }
}
