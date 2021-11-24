import java.time.OffsetDateTime;
import java.util.ArrayList;

public class ExcelTest {

    public static void main(String[] args) {
        Person person = new Person();
        person.setId(1);
        person.setName("张三");
        person.setBirthday(OffsetDateTime.parse("2020-08-08"));


        ArrayList<Person> people = new ArrayList<>();
        people.add(person);
    }
}
