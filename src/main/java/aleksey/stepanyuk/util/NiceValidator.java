package aleksey.stepanyuk.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NiceValidator {

    private Pattern pattern;
    private Matcher matcher;

    private static final String IMAGE_PATTERN =
            "([^\\s]+(\\.(?i)(jpg|jpeg|png|gif|bmp))$)";

    public NiceValidator() {
        pattern = Pattern.compile(IMAGE_PATTERN);
    }

    public boolean imageValidate(final String image) {

        matcher = pattern.matcher(image);

        return matcher.matches();
    }
}