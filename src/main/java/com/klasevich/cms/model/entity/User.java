package com.klasevich.cms.model.entity;

/**
 * @author Tatsiana Klasevich
 * The object User.
 */
public class User {
    private int id;
    private String name;
    private String surname;
    private String mail;
    private String phone;
    private String avatar;
    private Role role;

    /**
     * Instantiates a new User.
     */
    public User() {
    }

    /**
     * Instantiates a new User.
     *
     * @param name    the name
     * @param surname the surname
     * @param mail    the mail
     * @param phone   the phone
     */
    public User(String name, String surname, String mail, String phone) {
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.phone = phone;
    }

    /**
     * Instantiates a new User.
     *
     * @param name    the name
     * @param surname the surname
     * @param mail    the mail
     * @param phone   the phone
     * @param role    the role
     */
    public User(String name, String surname, String mail, String phone, Role role) {
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.phone = phone;
        this.role = role;
    }

    /**
     * Instantiates a new User.
     *
     * @param id      the id
     * @param name    the name
     * @param surname the surname
     * @param mail    the mail
     * @param phone   the phone
     * @param avatar  the avatar
     * @param role    the role
     */
    public User(int id, String name, String surname, String mail, String phone, String avatar, Role role) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.phone = phone;
        this.avatar = avatar;
        this.role = role;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets surname.
     *
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets surname.
     *
     * @param surname the surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Gets mail.
     *
     * @return the mail
     */
    public String getMail() {
        return mail;
    }

    /**
     * Sets mail.
     *
     * @param mail the mail
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * Gets phone.
     *
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets phone.
     *
     * @param phone the phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets avatar.
     *
     * @return the avatar
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * Sets avatar.
     *
     * @param avatar the avatar
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * Gets role.
     *
     * @return the role
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sets role.
     *
     * @param role the role
     */
    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (surname != null ? !surname.equals(user.surname) : user.surname != null) return false;
        if (mail != null ? !mail.equals(user.mail) : user.mail != null) return false;
        if (phone != null ? !phone.equals(user.phone) : user.phone != null) return false;
        return role == user.role;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (mail != null ? mail.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", surname='").append(surname).append('\'');
        sb.append(", mail='").append(mail).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", role=").append(role);
        sb.append('}');
        return sb.toString();
    }
}
