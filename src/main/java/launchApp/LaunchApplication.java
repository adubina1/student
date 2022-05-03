package launchApp;

import entity.Student;
import helper.UserCommunications;

public class LaunchApplication {

    public static void startApp() {
        UserCommunications.greetings();
        Student student = UserCommunications.chooseStudent();
        UserCommunications.reportSelection(student);
    }
}
