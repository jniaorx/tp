package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEPARTMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOCTOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOKNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOKPHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddPatientCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Department;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.NextOfKin;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;

class AddPatientCommandParserTest {

    private final AddPatientCommandParser parser = new AddPatientCommandParser();

    @Test
    void parse_allFieldsPresent_success() throws Exception {
        String userInput = " " + PREFIX_NAME + "John Doe "
                + PREFIX_PHONE + "98765432 "
                + PREFIX_EMAIL + "johnd@example.com "
                + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
                + PREFIX_DOCTOR + "Dr. Smith "
                + PREFIX_NOKNAME + "Jane Doe "
                + PREFIX_NOKPHONE + "98765438 "
                + PREFIX_DEPARTMENT + "Cardiology ";

        AddPatientCommand expectedCommand = new AddPatientCommand(
                new Patient(
                        new Name("John Doe"),
                        new Phone("98765432"),
                        new Email("johnd@example.com"),
                        new Address("311, Clementi Ave 2, #02-25"),
                        new Remark("NIL"),
                        "Dr. Smith",
                        new NextOfKin(new Name("Jane Doe"), new Phone("98765438")),
                        new Department("Cardiology")

                )
        );

        assertEquals(expectedCommand, parser.parse(userInput));
    }

    @Test
    void parse_missingName_throwsParseException() {
        String userInput = " " + PREFIX_PHONE + "98765432 "
                + PREFIX_EMAIL + "johnd@example.com "
                + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
                + PREFIX_DOCTOR + "Dr. Smith "
                + PREFIX_DEPARTMENT + "Cardiology ";

        assertThrows(ParseException.class, () -> parser.parse(userInput),
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPatientCommand.MESSAGE_USAGE));
    }

    @Test
    void parse_missingPhone_throwsParseException() {
        String userInput = " " + PREFIX_NAME + "John Doe "
                + PREFIX_EMAIL + "johnd@example.com "
                + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
                + PREFIX_DOCTOR + "Dr. Smith "
                + PREFIX_DEPARTMENT + "Cardiology ";

        assertThrows(ParseException.class, () -> parser.parse(userInput),
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPatientCommand.MESSAGE_USAGE));
    }

    @Test
    void parse_invalidPhone_throwsParseException() {
        String userInput = " " + PREFIX_NAME + "John Doe "
                + PREFIX_PHONE + "123abc " // Invalid phone
                + PREFIX_EMAIL + "johnd@example.com "
                + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
                + PREFIX_DOCTOR + "Dr. Smith "
                + PREFIX_DEPARTMENT + "Cardiology ";

        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }

    @Test
    void parse_invalidEmail_throwsParseException() {
        String userInput = " " + PREFIX_NAME + "John Doe "
                + PREFIX_PHONE + "98765432 "
                + PREFIX_EMAIL + "invalid-email " // Invalid email
                + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
                + PREFIX_DOCTOR + "Dr. Smith "
                + PREFIX_DEPARTMENT + "Cardiology ";

        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }

    @Test
    void parse_extraPreamble_throwsParseException() {
        String userInput = "extraText " + PREFIX_NAME + "John Doe "
                + PREFIX_PHONE + "98765432 "
                + PREFIX_EMAIL + "johnd@example.com "
                + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
                + PREFIX_DOCTOR + "Dr. Smith "
                + PREFIX_DEPARTMENT + "Cardiology ";

        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }

    @Test
    void parse_duplicatePrefixes_throwsParseException() {
        String userInput = " " + PREFIX_NAME + "John Doe "
                + PREFIX_NAME + "Jane Doe " // Duplicate
                + PREFIX_PHONE + "98765432 "
                + PREFIX_EMAIL + "johnd@example.com "
                + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
                + PREFIX_DOCTOR + "Dr. Smith "
                + PREFIX_DEPARTMENT + "Cardiology ";

        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }

    @Test
    @Disabled("No longer an issue")
    void parse_missingEmail_usesDefaultEmail() throws Exception {
        String userInput = " " + PREFIX_NAME + "John Doe "
                + PREFIX_PHONE + "98765432 "
                + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
                + PREFIX_DOCTOR + "Dr.Smith "
                + PREFIX_DEPARTMENT + "Cardiology ";

        AddPatientCommand expectedCommand = new AddPatientCommand(
                new Patient(
                        new Name("John Doe"),
                        new Phone("98765432"),
                        new Email("NA@placeholder.com"), // Uses default email
                        new Address("311, Clementi Ave 2, #02-25"),
                        new Remark("serious"),
                        "Dr Smith",
                        new NextOfKin(new Name("Jane Doe"), new Phone("98765432")),
                        new Department("Cardiology")
                )
        );

        assertEquals(expectedCommand, parser.parse(userInput));
    }

    @Test
    @Disabled("No longer an issue")
    void parse_missingAddress_usesDefaultAddress() throws Exception {
        String userInput = " " + PREFIX_NAME + "John Doe "
                + PREFIX_PHONE + "98765432 "
                + PREFIX_EMAIL + "johnd@example.com "
                + PREFIX_DOCTOR + "Dr.Smith "
                + PREFIX_DEPARTMENT + "Cardiology ";

        AddPatientCommand expectedCommand = new AddPatientCommand(
                new Patient(
                        new Name("John Doe"),
                        new Phone("98765432"),
                        new Email("johnd@example.com"),
                        new Address("NA"),
                        new Remark(""),
                        "Dr Smith",
                        new NextOfKin(new Name("Jane Doe"), new Phone("98765432")),
                        new Department("Cardiology")
                )
        );

        assertEquals(expectedCommand, parser.parse(userInput));
    }

}
