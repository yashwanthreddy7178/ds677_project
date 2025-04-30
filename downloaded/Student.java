/**
 * Student Class.
 * @author yuxiz
 */
public class Student {
    /**
     *firstName of Student.
     */
    private String firstName;
    /**
     *lastName of Student.
     */
    private String lastName;
    /**
     *phoneNumber of Student.
     */
    private String phoneNumber;
    /**
     *andrewId of Student.
     */
    private String andrewId;
    /**
     *Constructor with andrewId.
     *@param andrewId value of Student
     */
    public Student(String andrewId) {
        this.andrewId = andrewId;
    }
    /**
     *Return the andrewId.
     *@return andrewId
     */
    public String getAndrewId() {
        return andrewId;
    }
    /**
     *Return firstname of student.
     *@return first name of student
     */
    public String getFirstName() {
        return firstName;
    }
    /**
     *Return lastname of student.
     *@return last name of student
     */
    public String getLastName() {
        return lastName;
    }
    /**
     *Return the phone number.
     *@return phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
    /**
     *Set the firstname.
     *@param firstName value of Student
     */
    public void setFirstName(String firstName) {
        if (this.firstName != null) {
            return;
        }
        this.firstName = firstName;
    }
    /**
     *Set the lastname.
     *@param lastName value of Student
     */
    public void setLastName(String lastName) {
        if (this.lastName != null) {
            return;
        }
        this.lastName = lastName;
    }
    /**
     *Set the phone number.
     *@param phoneNumber value of Student
     */
    public void setPhoneNumber(String phoneNumber) {
        if (this.phoneNumber != null) {
            return;
        }
        this.phoneNumber = phoneNumber;
    }
    /**
     *Returns string representation of each student.
     *@return a string
     */
    public String toString() {
        String s = this.firstName + " " + this.lastName + " (Andrew ID: "
                + this.andrewId + ", Phone Number: " + this.phoneNumber
                + ") \n";
        return s;
    }
}
