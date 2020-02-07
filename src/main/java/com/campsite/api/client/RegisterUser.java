package com.campsite.api.client;

import com.okta.sdk.resource.group.GroupList;
import com.okta.sdk.resource.user.*;
import com.okta.sdk.resource.user.User;
import com.okta.sdk.resource.user.factor.Factor;
import com.okta.sdk.resource.user.factor.FactorList;
import com.okta.sdk.resource.user.factor.SecurityQuestionList;

import java.util.*;

public class RegisterUser implements User {

    @Override
    public Map<String, Object> getEmbedded() {
        return null;
    }

    @Override
    public Map<String, Object> getLinks() {
        return null;
    }

    @Override
    public Date getActivated() {
        return null;
    }

    @Override
    public Date getCreated() {
        return null;
    }

    @Override
    public UserCredentials getCredentials() {
        return null;
    }

    @Override
    public User setCredentials(UserCredentials userCredentials) {
        return null;
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public Date getLastLogin() {
        return null;
    }

    @Override
    public Date getLastUpdated() {
        return null;
    }

    @Override
    public Date getPasswordChanged() {
        return null;
    }

    @Override
    public UserProfile getProfile() {
        return null;
    }

    @Override
    public User setProfile(UserProfile userProfile) {
        return null;
    }

    @Override
    public UserStatus getStatus() {
        return null;
    }

    @Override
    public Date getStatusChanged() {
        return null;
    }

    @Override
    public UserStatus getTransitioningToStatus() {
        return null;
    }

    @Override
    public void deactivate(Boolean aBoolean) {

    }

    @Override
    public void deactivate() {

    }

    @Override
    public ResetPasswordToken resetPassword(String s, Boolean aBoolean) {
        return null;
    }

    @Override
    public ResetPasswordToken resetPassword() {
        return null;
    }

    @Override
    public FactorList listFactors() {
        return null;
    }

    @Override
    public GroupList listGroupTargetsForRole(String s) {
        return null;
    }

    @Override
    public ForgotPasswordResponse forgotPassword(UserCredentials userCredentials, Boolean aBoolean) {
        return null;
    }

    @Override
    public ForgotPasswordResponse forgotPassword() {
        return null;
    }

    @Override
    public void removeRole(String s) {

    }

    @Override
    public TempPassword expirePassword(Boolean aBoolean) {
        return null;
    }

    @Override
    public TempPassword expirePassword() {
        return null;
    }

    @Override
    public UserActivationToken activate(Boolean aBoolean) {
        return null;
    }

    @Override
    public UserCredentials changeRecoveryQuestion(UserCredentials userCredentials) {
        return null;
    }

    @Override
    public void unsuspend() {

    }

    @Override
    public Factor addFactor(Factor factor, Boolean aBoolean, String s, Integer integer, Boolean aBoolean1) {
        return null;
    }

    @Override
    public Factor addFactor(Factor factor) {
        return null;
    }

    @Override
    public Factor addFactor(Factor factor, Boolean aBoolean, String s) {
        return null;
    }

    @Override
    public GroupList listGroups() {
        return null;
    }

    @Override
    public void removeGroupTargetFromRole(String s, String s1) {

    }

    @Override
    public FactorList listSupportedFactors() {
        return null;
    }

    @Override
    public void delete(Boolean aBoolean) {

    }

    @Override
    public void delete() {

    }

    @Override
    public void resetFactors() {

    }

    @Override
    public void suspend() {

    }

    @Override
    public RoleList listRoles(String s) {
        return null;
    }

    @Override
    public RoleList listRoles() {
        return null;
    }

    @Override
    public void unlock() {

    }

    @Override
    public User update() {
        return null;
    }

    @Override
    public Factor getFactor(String s) {
        return null;
    }

    @Override
    public UserCredentials changePassword(ChangePasswordRequest changePasswordRequest) {
        return null;
    }

    @Override
    public AppLinkList listAppLinks(Boolean aBoolean) {
        return null;
    }

    @Override
    public AppLinkList listAppLinks() {
        return null;
    }

    @Override
    public void addGroupTargetToRole(String s, String s1) {

    }

    @Override
    public SecurityQuestionList listSupportedSecurityQuestions() {
        return null;
    }

    @Override
    public void endAllSessions(Boolean aBoolean) {

    }

    @Override
    public void endAllSessions() {

    }

    @Override
    public void addToGroup(String s) {

    }

    @Override
    public Role addRole(Role role) {
        return null;
    }

    @Override
    public Factor addFactor(Boolean aBoolean, String s, Integer integer, Boolean aBoolean1, Factor factor) {
        return null;
    }

    @Override
    public Factor addFactor(Boolean aBoolean, String s, Factor factor) {
        return null;
    }

    @Override
    public ForgotPasswordResponse forgotPassword(Boolean aBoolean, UserCredentials userCredentials) {
        return null;
    }

    @Override
    public String getString(String s) {
        return null;
    }

    @Override
    public Double getNumber(String s) {
        return null;
    }

    @Override
    public Boolean getBoolean(String s) {
        return null;
    }

    @Override
    public Integer getInteger(String s) {
        return null;
    }

    @Override
    public List<String> getStringList(String s) {
        return null;
    }

    @Override
    public List<Double> getNumberList(String s) {
        return null;
    }

    @Override
    public List<Integer> getIntegerList(String s) {
        return null;
    }

    @Override
    public String getResourceHref() {
        return null;
    }

    @Override
    public void setResourceHref(String s) {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public Object get(Object key) {
        return null;
    }

    @Override
    public Object put(String key, Object value) {
        return null;
    }

    @Override
    public Object remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map<? extends String, ?> m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<String> keySet() {
        return null;
    }

    @Override
    public Collection<Object> values() {
        return null;
    }

    @Override
    public Set<Entry<String, Object>> entrySet() {
        return null;
    }
}
