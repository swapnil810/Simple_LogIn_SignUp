package com.swapnil.task.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * This class is used as .
 *
 * @author CanopusInfoSystems
 * @version 1.0
 * @since 19/3/21 :March : 2021 on 17 : 58.
 */
class LogInDetailsModel implements Serializable {


    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("role_id")
    @Expose
    private Integer roleId;
    @SerializedName("role_after_social_login")
    @Expose
    private Integer roleAfterSocialLogin;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("display_name")
    @Expose
    private String displayName;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("paypal_email")
    @Expose
    private String paypalEmail;
    @SerializedName("profile_pic")
    @Expose
    private String profilePic;
    @SerializedName("banner_image")
    @Expose
    private String bannerImage;
    @SerializedName("bio_video")
    @Expose
    private String bioVideo;
    @SerializedName("public_profile")
    @Expose
    private String publicProfile;
    @SerializedName("api_token")
    @Expose
    private String apiToken;
    @SerializedName("provider_id")
    @Expose
    private Object providerId;
    @SerializedName("provider")
    @Expose
    private Object provider;
    @SerializedName("provider_token")
    @Expose
    private Object providerToken;
    @SerializedName("email_verified")
    @Expose
    private String emailVerified;
    @SerializedName("vacation_mode")
    @Expose
    private String vacationMode;
    @SerializedName("experience_level")
    @Expose
    private String experienceLevel;
    @SerializedName("description")
    @Expose
    private Object description;
    @SerializedName("visibilty")
    @Expose
    private String visibilty;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("zip_code")
    @Expose
    private String zipCode;
    @SerializedName("created_by")
    @Expose
    private Integer createdBy;
    @SerializedName("updated_by")
    @Expose
    private Integer updatedBy;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("auto_reply")
    @Expose
    private Integer autoReply;
    @SerializedName("automatic_message")
    @Expose
    private String automaticMessage;
    @SerializedName("counter")
    @Expose
    private Object counter;
    @SerializedName("stripe_customer_id")
    @Expose
    private Object stripeCustomerId;
    @SerializedName("card_last_four_digit")
    @Expose
    private Object cardLastFourDigit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getRoleAfterSocialLogin() {
        return roleAfterSocialLogin;
    }

    public void setRoleAfterSocialLogin(Integer roleAfterSocialLogin) {
        this.roleAfterSocialLogin = roleAfterSocialLogin;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPaypalEmail() {
        return paypalEmail;
    }

    public void setPaypalEmail(String paypalEmail) {
        this.paypalEmail = paypalEmail;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getBannerImage() {
        return bannerImage;
    }

    public void setBannerImage(String bannerImage) {
        this.bannerImage = bannerImage;
    }

    public String getBioVideo() {
        return bioVideo;
    }

    public void setBioVideo(String bioVideo) {
        this.bioVideo = bioVideo;
    }

    public String getPublicProfile() {
        return publicProfile;
    }

    public void setPublicProfile(String publicProfile) {
        this.publicProfile = publicProfile;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public Object getProviderId() {
        return providerId;
    }

    public void setProviderId(Object providerId) {
        this.providerId = providerId;
    }

    public Object getProvider() {
        return provider;
    }

    public void setProvider(Object provider) {
        this.provider = provider;
    }

    public Object getProviderToken() {
        return providerToken;
    }

    public void setProviderToken(Object providerToken) {
        this.providerToken = providerToken;
    }

    public String getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(String emailVerified) {
        this.emailVerified = emailVerified;
    }

    public String getVacationMode() {
        return vacationMode;
    }

    public void setVacationMode(String vacationMode) {
        this.vacationMode = vacationMode;
    }

    public String getExperienceLevel() {
        return experienceLevel;
    }

    public void setExperienceLevel(String experienceLevel) {
        this.experienceLevel = experienceLevel;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public String getVisibilty() {
        return visibilty;
    }

    public void setVisibilty(String visibilty) {
        this.visibilty = visibilty;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getAutoReply() {
        return autoReply;
    }

    public void setAutoReply(Integer autoReply) {
        this.autoReply = autoReply;
    }

    public String getAutomaticMessage() {
        return automaticMessage;
    }

    public void setAutomaticMessage(String automaticMessage) {
        this.automaticMessage = automaticMessage;
    }

    public Object getCounter() {
        return counter;
    }

    public void setCounter(Object counter) {
        this.counter = counter;
    }

    public Object getStripeCustomerId() {
        return stripeCustomerId;
    }

    public void setStripeCustomerId(Object stripeCustomerId) {
        this.stripeCustomerId = stripeCustomerId;
    }

    public Object getCardLastFourDigit() {
        return cardLastFourDigit;
    }

    public void setCardLastFourDigit(Object cardLastFourDigit) {
        this.cardLastFourDigit = cardLastFourDigit;
    }
}
