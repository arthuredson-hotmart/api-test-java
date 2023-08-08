package test.com;

public enum Browser implements EnumBase<Integer> {
    EDGE(6),
    CHROME(1),
    FIREFOX(2),
    SAFARI(3),
    INTERNET_EXPLORER(4),
    OPERA(5),
    FACEBOOK_APP(8),
    OTHER(7);

    private int id;

    private Browser(int id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }
}
