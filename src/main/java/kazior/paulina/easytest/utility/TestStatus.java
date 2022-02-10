package kazior.paulina.easytest.utility;

public enum TestStatus {

    PASS("Pass"),
    FAIL("Fail"),
    NOT_STARTED("Not started"),
    IN_PROGRESS("In progress"),
    BLOCKED("Blocked");

    private String name;

    TestStatus(String name) {
        this.name = name;
    }
}
