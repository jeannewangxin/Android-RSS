package fr.ws.reader.bean;

import java.io.Serializable;

/**
 * 用户的个人信息
 */
public class Account implements Serializable {

    private int entity_id;
    private String password;
    private String nickname;
    private String firstname;
    private String lastname;
    private String company;
    private String siret;
    private String no_tva;
    private String telephone;
    private String email;
    private String street;
    private String floor;
    private String intercom;
    private String door;
    private String zip;
    private String district;
    private String city;
    private String region;
    private String country;
    private String orthers;
    private float ord;
    private float cre;
    private float pay;
    private String created;
    private String username;
    private int is_active;
    private String device_token;
    private int level;
    private String validate;
    private int guest;
    private String token_pay;


    public String getTokenPay() {
        return token_pay;
    }

    public void setTokenPay(String token_pay) {
        this.token_pay = token_pay;
    }
    public int getEntityId() {
        return entity_id;
    }

    public void setEntityId(int entity_id) {
        this.entity_id = entity_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSiret() {
        return siret;
    }

    public void setSiret(String siret) {
        this.siret = siret;
    }

    public String getNoTva() {
        return no_tva;
    }

    public void setNoTva(String no_tva) {
        this.no_tva = no_tva;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getIntercom() {
        return intercom;
    }

    public void setIntercom(String intercom) {
        this.intercom = intercom;
    }

    public String getDoor() {
        return door;
    }

    public void setDoor(String door) {
        this.door = door;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getOrthers() {
        return orthers;
    }

    public void setOrthers(String orthers) {
        this.orthers = orthers;
    }

    public float getOrd() {
        return ord;
    }

    public void setOrd(float ord) {
        this.ord = ord;
    }

    public float getCre() {
        return cre;
    }

    public void setCre(float cre) {
        this.cre = cre;
    }

    public float getPay() {
        return pay;
    }

    public void setPay(float pay) {
        this.pay = pay;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getIsActive() {
        return is_active;
    }

    public void setIsActive(int is_active) {
        this.is_active = is_active;
    }

    public String getDeviceToken() {
        return device_token;
    }

    public void setDeviceToken(String device_token) {
        this.device_token = device_token;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getValidate() {
        return validate;
    }

    public void setValidate(String validate) {
        this.validate = validate;
    }

    public int getGuest() {
        return guest;
    }

    public void setGuest(int guest) {
        this.guest = guest;
    }





}
