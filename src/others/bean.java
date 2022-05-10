package others;

import java.util.Objects;

public class bean {
    private String id;
    private String name;
    private String address;
    private String phonenum;

    public bean() {
    }

    public bean(String id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "bean{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        bean bean = (bean) o;
        return Objects.equals(id, bean.id) && Objects.equals(name, bean.name) && Objects.equals(address, bean.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address);
    }
}
