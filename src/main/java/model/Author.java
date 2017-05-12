package model;


public class Author extends Item {
    private String phone;

    public String getPhone() {
        return phone;
    }

    public Author(long id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return String.format("%s Phone: %s", super.toString(), phone);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author)) return false;
        if (!super.equals(o)) return false;

        Author author = (Author) o;

        return phone != null ? phone.equals(author.phone) : author.phone == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        return result;
    }
}
