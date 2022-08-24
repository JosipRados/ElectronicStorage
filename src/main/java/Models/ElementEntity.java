package Models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class ElementEntity {
    public long id;
    public String code;
    public String value;
    public String unit;
    public String type;
    public String size;
    public String comment;
}
