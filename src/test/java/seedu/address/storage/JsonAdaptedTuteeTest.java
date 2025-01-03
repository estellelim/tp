package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedTutee.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.DANIEL;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

public class JsonAdaptedTuteeTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_HOURS = "-69";
    private static final String INVALID_SUBJECTS = "wrong";

    private static final String VALID_NAME = DANIEL.getName().toString();
    private static final String VALID_PHONE = DANIEL.getPhone().toString();
    private static final String VALID_EMAIL = DANIEL.getEmail().toString();
    private static final String VALID_ADDRESS = DANIEL.getAddress().toString();
    private static final String VALID_HOURS = "69";
    private static final List<JsonAdaptedSubject> VALID_SUBJECTS = DANIEL.getSubjects().stream()
            .map(JsonAdaptedSubject::new).collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedTutee tutee = new JsonAdaptedTutee(DANIEL);
        assertEquals(DANIEL, tutee.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedTutee tutee =
                new JsonAdaptedTutee(0, INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_HOURS,
                        VALID_SUBJECTS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, tutee::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedTutee tutee = new JsonAdaptedTutee(0, null, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_HOURS, VALID_SUBJECTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, tutee::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedTutee tutee =
                new JsonAdaptedTutee(0, VALID_NAME, INVALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, VALID_HOURS, VALID_SUBJECTS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, tutee::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedTutee tutee = new JsonAdaptedTutee(0, VALID_NAME, null, VALID_EMAIL,
                VALID_ADDRESS, VALID_HOURS, VALID_SUBJECTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, tutee::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedTutee tutee =
                new JsonAdaptedTutee(0, VALID_NAME, VALID_PHONE, INVALID_EMAIL,
                        VALID_ADDRESS, VALID_HOURS, VALID_SUBJECTS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, tutee::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedTutee tutee = new JsonAdaptedTutee(0, VALID_NAME, VALID_PHONE, null,
                VALID_ADDRESS, VALID_HOURS, VALID_SUBJECTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, tutee::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedTutee tutee =
                new JsonAdaptedTutee(0, VALID_NAME, VALID_PHONE, VALID_EMAIL,
                        INVALID_ADDRESS, VALID_HOURS, VALID_SUBJECTS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, tutee::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedTutee tutee = new JsonAdaptedTutee(0, VALID_NAME, VALID_PHONE, VALID_EMAIL,
                null, VALID_HOURS, VALID_SUBJECTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, tutee::toModelType);
    }

    @Test
    public void toModelType_invalidHours_throwsIllegalValueException() {
        JsonAdaptedTutee tutee =
                new JsonAdaptedTutee(0, VALID_NAME, VALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, INVALID_HOURS, VALID_SUBJECTS);
        assertThrows(IllegalValueException.class, tutee::toModelType);
    }

    @Test
    public void toModelType_nullHours_throwsIllegalValueException() {
        JsonAdaptedTutee tutee =
                new JsonAdaptedTutee(0, VALID_NAME, VALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, null, VALID_SUBJECTS);
        assertThrows(IllegalValueException.class, tutee::toModelType);
    }

    @Test
    public void toModelType_invalidSubject_throwsIllegalValueException() {
        List<JsonAdaptedSubject> invalidSubjects = new ArrayList<>(VALID_SUBJECTS);
        invalidSubjects.add(new JsonAdaptedSubject(INVALID_SUBJECTS));
        JsonAdaptedTutee tutee =
                new JsonAdaptedTutee(0, VALID_NAME, VALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, INVALID_HOURS, invalidSubjects);
        assertThrows(IllegalValueException.class, tutee::toModelType);
    }
}
