import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class Person {

    /**
     * id
     */
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 生日
     */
    private OffsetDateTime birthday;
}
