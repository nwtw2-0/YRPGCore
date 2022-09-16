package net.yuziouo.StorageSystem;

public enum StorageType {
    test("test"),
    town("Town"),
    grade("Grade");
    private final String name;
    StorageType(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
