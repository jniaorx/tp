package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_ROLE = new Prefix("r/");
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_REMARK = new Prefix("rm/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_NOKNAME = new Prefix("nn/");
    public static final Prefix PREFIX_NOKPHONE = new Prefix("np/");
    public static final Prefix PREFIX_DOCTOR = new Prefix("dr/");
    public static final Prefix PREFIX_DEPARTMENT = new Prefix("dp/");
}
