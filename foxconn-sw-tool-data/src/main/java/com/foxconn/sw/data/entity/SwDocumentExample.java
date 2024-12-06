package com.foxconn.sw.data.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SwDocumentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SwDocumentExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andDocumentNameIsNull() {
            addCriterion("document_name is null");
            return (Criteria) this;
        }

        public Criteria andDocumentNameIsNotNull() {
            addCriterion("document_name is not null");
            return (Criteria) this;
        }

        public Criteria andDocumentNameEqualTo(String value) {
            addCriterion("document_name =", value, "documentName");
            return (Criteria) this;
        }

        public Criteria andDocumentNameNotEqualTo(String value) {
            addCriterion("document_name <>", value, "documentName");
            return (Criteria) this;
        }

        public Criteria andDocumentNameGreaterThan(String value) {
            addCriterion("document_name >", value, "documentName");
            return (Criteria) this;
        }

        public Criteria andDocumentNameGreaterThanOrEqualTo(String value) {
            addCriterion("document_name >=", value, "documentName");
            return (Criteria) this;
        }

        public Criteria andDocumentNameLessThan(String value) {
            addCriterion("document_name <", value, "documentName");
            return (Criteria) this;
        }

        public Criteria andDocumentNameLessThanOrEqualTo(String value) {
            addCriterion("document_name <=", value, "documentName");
            return (Criteria) this;
        }

        public Criteria andDocumentNameLike(String value) {
            addCriterion("document_name like", value, "documentName");
            return (Criteria) this;
        }

        public Criteria andDocumentNameNotLike(String value) {
            addCriterion("document_name not like", value, "documentName");
            return (Criteria) this;
        }

        public Criteria andDocumentNameIn(List<String> values) {
            addCriterion("document_name in", values, "documentName");
            return (Criteria) this;
        }

        public Criteria andDocumentNameNotIn(List<String> values) {
            addCriterion("document_name not in", values, "documentName");
            return (Criteria) this;
        }

        public Criteria andDocumentNameBetween(String value1, String value2) {
            addCriterion("document_name between", value1, value2, "documentName");
            return (Criteria) this;
        }

        public Criteria andDocumentNameNotBetween(String value1, String value2) {
            addCriterion("document_name not between", value1, value2, "documentName");
            return (Criteria) this;
        }

        public Criteria andFileVersionIsNull() {
            addCriterion("file_version is null");
            return (Criteria) this;
        }

        public Criteria andFileVersionIsNotNull() {
            addCriterion("file_version is not null");
            return (Criteria) this;
        }

        public Criteria andFileVersionEqualTo(String value) {
            addCriterion("file_version =", value, "fileVersion");
            return (Criteria) this;
        }

        public Criteria andFileVersionNotEqualTo(String value) {
            addCriterion("file_version <>", value, "fileVersion");
            return (Criteria) this;
        }

        public Criteria andFileVersionGreaterThan(String value) {
            addCriterion("file_version >", value, "fileVersion");
            return (Criteria) this;
        }

        public Criteria andFileVersionGreaterThanOrEqualTo(String value) {
            addCriterion("file_version >=", value, "fileVersion");
            return (Criteria) this;
        }

        public Criteria andFileVersionLessThan(String value) {
            addCriterion("file_version <", value, "fileVersion");
            return (Criteria) this;
        }

        public Criteria andFileVersionLessThanOrEqualTo(String value) {
            addCriterion("file_version <=", value, "fileVersion");
            return (Criteria) this;
        }

        public Criteria andFileVersionLike(String value) {
            addCriterion("file_version like", value, "fileVersion");
            return (Criteria) this;
        }

        public Criteria andFileVersionNotLike(String value) {
            addCriterion("file_version not like", value, "fileVersion");
            return (Criteria) this;
        }

        public Criteria andFileVersionIn(List<String> values) {
            addCriterion("file_version in", values, "fileVersion");
            return (Criteria) this;
        }

        public Criteria andFileVersionNotIn(List<String> values) {
            addCriterion("file_version not in", values, "fileVersion");
            return (Criteria) this;
        }

        public Criteria andFileVersionBetween(String value1, String value2) {
            addCriterion("file_version between", value1, value2, "fileVersion");
            return (Criteria) this;
        }

        public Criteria andFileVersionNotBetween(String value1, String value2) {
            addCriterion("file_version not between", value1, value2, "fileVersion");
            return (Criteria) this;
        }

        public Criteria andCategoryIsNull() {
            addCriterion("category is null");
            return (Criteria) this;
        }

        public Criteria andCategoryIsNotNull() {
            addCriterion("category is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryEqualTo(String value) {
            addCriterion("category =", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotEqualTo(String value) {
            addCriterion("category <>", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryGreaterThan(String value) {
            addCriterion("category >", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryGreaterThanOrEqualTo(String value) {
            addCriterion("category >=", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLessThan(String value) {
            addCriterion("category <", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLessThanOrEqualTo(String value) {
            addCriterion("category <=", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLike(String value) {
            addCriterion("category like", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotLike(String value) {
            addCriterion("category not like", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryIn(List<String> values) {
            addCriterion("category in", values, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotIn(List<String> values) {
            addCriterion("category not in", values, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryBetween(String value1, String value2) {
            addCriterion("category between", value1, value2, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotBetween(String value1, String value2) {
            addCriterion("category not between", value1, value2, "category");
            return (Criteria) this;
        }

        public Criteria andDepartmentIsNull() {
            addCriterion("department is null");
            return (Criteria) this;
        }

        public Criteria andDepartmentIsNotNull() {
            addCriterion("department is not null");
            return (Criteria) this;
        }

        public Criteria andDepartmentEqualTo(Integer value) {
            addCriterion("department =", value, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentNotEqualTo(Integer value) {
            addCriterion("department <>", value, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentGreaterThan(Integer value) {
            addCriterion("department >", value, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentGreaterThanOrEqualTo(Integer value) {
            addCriterion("department >=", value, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentLessThan(Integer value) {
            addCriterion("department <", value, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentLessThanOrEqualTo(Integer value) {
            addCriterion("department <=", value, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentIn(List<Integer> values) {
            addCriterion("department in", values, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentNotIn(List<Integer> values) {
            addCriterion("department not in", values, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentBetween(Integer value1, Integer value2) {
            addCriterion("department between", value1, value2, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentNotBetween(Integer value1, Integer value2) {
            addCriterion("department not between", value1, value2, "department");
            return (Criteria) this;
        }

        public Criteria andProjectIsNull() {
            addCriterion("project is null");
            return (Criteria) this;
        }

        public Criteria andProjectIsNotNull() {
            addCriterion("project is not null");
            return (Criteria) this;
        }

        public Criteria andProjectEqualTo(String value) {
            addCriterion("project =", value, "project");
            return (Criteria) this;
        }

        public Criteria andProjectNotEqualTo(String value) {
            addCriterion("project <>", value, "project");
            return (Criteria) this;
        }

        public Criteria andProjectGreaterThan(String value) {
            addCriterion("project >", value, "project");
            return (Criteria) this;
        }

        public Criteria andProjectGreaterThanOrEqualTo(String value) {
            addCriterion("project >=", value, "project");
            return (Criteria) this;
        }

        public Criteria andProjectLessThan(String value) {
            addCriterion("project <", value, "project");
            return (Criteria) this;
        }

        public Criteria andProjectLessThanOrEqualTo(String value) {
            addCriterion("project <=", value, "project");
            return (Criteria) this;
        }

        public Criteria andProjectLike(String value) {
            addCriterion("project like", value, "project");
            return (Criteria) this;
        }

        public Criteria andProjectNotLike(String value) {
            addCriterion("project not like", value, "project");
            return (Criteria) this;
        }

        public Criteria andProjectIn(List<String> values) {
            addCriterion("project in", values, "project");
            return (Criteria) this;
        }

        public Criteria andProjectNotIn(List<String> values) {
            addCriterion("project not in", values, "project");
            return (Criteria) this;
        }

        public Criteria andProjectBetween(String value1, String value2) {
            addCriterion("project between", value1, value2, "project");
            return (Criteria) this;
        }

        public Criteria andProjectNotBetween(String value1, String value2) {
            addCriterion("project not between", value1, value2, "project");
            return (Criteria) this;
        }

        public Criteria andSecretLevelIsNull() {
            addCriterion("secret_level is null");
            return (Criteria) this;
        }

        public Criteria andSecretLevelIsNotNull() {
            addCriterion("secret_level is not null");
            return (Criteria) this;
        }

        public Criteria andSecretLevelEqualTo(Integer value) {
            addCriterion("secret_level =", value, "secretLevel");
            return (Criteria) this;
        }

        public Criteria andSecretLevelNotEqualTo(Integer value) {
            addCriterion("secret_level <>", value, "secretLevel");
            return (Criteria) this;
        }

        public Criteria andSecretLevelGreaterThan(Integer value) {
            addCriterion("secret_level >", value, "secretLevel");
            return (Criteria) this;
        }

        public Criteria andSecretLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("secret_level >=", value, "secretLevel");
            return (Criteria) this;
        }

        public Criteria andSecretLevelLessThan(Integer value) {
            addCriterion("secret_level <", value, "secretLevel");
            return (Criteria) this;
        }

        public Criteria andSecretLevelLessThanOrEqualTo(Integer value) {
            addCriterion("secret_level <=", value, "secretLevel");
            return (Criteria) this;
        }

        public Criteria andSecretLevelIn(List<Integer> values) {
            addCriterion("secret_level in", values, "secretLevel");
            return (Criteria) this;
        }

        public Criteria andSecretLevelNotIn(List<Integer> values) {
            addCriterion("secret_level not in", values, "secretLevel");
            return (Criteria) this;
        }

        public Criteria andSecretLevelBetween(Integer value1, Integer value2) {
            addCriterion("secret_level between", value1, value2, "secretLevel");
            return (Criteria) this;
        }

        public Criteria andSecretLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("secret_level not between", value1, value2, "secretLevel");
            return (Criteria) this;
        }

        public Criteria andExpireDateIsNull() {
            addCriterion("expire_date is null");
            return (Criteria) this;
        }

        public Criteria andExpireDateIsNotNull() {
            addCriterion("expire_date is not null");
            return (Criteria) this;
        }

        public Criteria andExpireDateEqualTo(String value) {
            addCriterion("expire_date =", value, "expireDate");
            return (Criteria) this;
        }

        public Criteria andExpireDateNotEqualTo(String value) {
            addCriterion("expire_date <>", value, "expireDate");
            return (Criteria) this;
        }

        public Criteria andExpireDateGreaterThan(String value) {
            addCriterion("expire_date >", value, "expireDate");
            return (Criteria) this;
        }

        public Criteria andExpireDateGreaterThanOrEqualTo(String value) {
            addCriterion("expire_date >=", value, "expireDate");
            return (Criteria) this;
        }

        public Criteria andExpireDateLessThan(String value) {
            addCriterion("expire_date <", value, "expireDate");
            return (Criteria) this;
        }

        public Criteria andExpireDateLessThanOrEqualTo(String value) {
            addCriterion("expire_date <=", value, "expireDate");
            return (Criteria) this;
        }

        public Criteria andExpireDateLike(String value) {
            addCriterion("expire_date like", value, "expireDate");
            return (Criteria) this;
        }

        public Criteria andExpireDateNotLike(String value) {
            addCriterion("expire_date not like", value, "expireDate");
            return (Criteria) this;
        }

        public Criteria andExpireDateIn(List<String> values) {
            addCriterion("expire_date in", values, "expireDate");
            return (Criteria) this;
        }

        public Criteria andExpireDateNotIn(List<String> values) {
            addCriterion("expire_date not in", values, "expireDate");
            return (Criteria) this;
        }

        public Criteria andExpireDateBetween(String value1, String value2) {
            addCriterion("expire_date between", value1, value2, "expireDate");
            return (Criteria) this;
        }

        public Criteria andExpireDateNotBetween(String value1, String value2) {
            addCriterion("expire_date not between", value1, value2, "expireDate");
            return (Criteria) this;
        }

        public Criteria andDisableDownIsNull() {
            addCriterion("disable_down is null");
            return (Criteria) this;
        }

        public Criteria andDisableDownIsNotNull() {
            addCriterion("disable_down is not null");
            return (Criteria) this;
        }

        public Criteria andDisableDownEqualTo(Integer value) {
            addCriterion("disable_down =", value, "disableDown");
            return (Criteria) this;
        }

        public Criteria andDisableDownNotEqualTo(Integer value) {
            addCriterion("disable_down <>", value, "disableDown");
            return (Criteria) this;
        }

        public Criteria andDisableDownGreaterThan(Integer value) {
            addCriterion("disable_down >", value, "disableDown");
            return (Criteria) this;
        }

        public Criteria andDisableDownGreaterThanOrEqualTo(Integer value) {
            addCriterion("disable_down >=", value, "disableDown");
            return (Criteria) this;
        }

        public Criteria andDisableDownLessThan(Integer value) {
            addCriterion("disable_down <", value, "disableDown");
            return (Criteria) this;
        }

        public Criteria andDisableDownLessThanOrEqualTo(Integer value) {
            addCriterion("disable_down <=", value, "disableDown");
            return (Criteria) this;
        }

        public Criteria andDisableDownIn(List<Integer> values) {
            addCriterion("disable_down in", values, "disableDown");
            return (Criteria) this;
        }

        public Criteria andDisableDownNotIn(List<Integer> values) {
            addCriterion("disable_down not in", values, "disableDown");
            return (Criteria) this;
        }

        public Criteria andDisableDownBetween(Integer value1, Integer value2) {
            addCriterion("disable_down between", value1, value2, "disableDown");
            return (Criteria) this;
        }

        public Criteria andDisableDownNotBetween(Integer value1, Integer value2) {
            addCriterion("disable_down not between", value1, value2, "disableDown");
            return (Criteria) this;
        }

        public Criteria andResourceIdIsNull() {
            addCriterion("resource_id is null");
            return (Criteria) this;
        }

        public Criteria andResourceIdIsNotNull() {
            addCriterion("resource_id is not null");
            return (Criteria) this;
        }

        public Criteria andResourceIdEqualTo(Integer value) {
            addCriterion("resource_id =", value, "resourceId");
            return (Criteria) this;
        }

        public Criteria andResourceIdNotEqualTo(Integer value) {
            addCriterion("resource_id <>", value, "resourceId");
            return (Criteria) this;
        }

        public Criteria andResourceIdGreaterThan(Integer value) {
            addCriterion("resource_id >", value, "resourceId");
            return (Criteria) this;
        }

        public Criteria andResourceIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("resource_id >=", value, "resourceId");
            return (Criteria) this;
        }

        public Criteria andResourceIdLessThan(Integer value) {
            addCriterion("resource_id <", value, "resourceId");
            return (Criteria) this;
        }

        public Criteria andResourceIdLessThanOrEqualTo(Integer value) {
            addCriterion("resource_id <=", value, "resourceId");
            return (Criteria) this;
        }

        public Criteria andResourceIdIn(List<Integer> values) {
            addCriterion("resource_id in", values, "resourceId");
            return (Criteria) this;
        }

        public Criteria andResourceIdNotIn(List<Integer> values) {
            addCriterion("resource_id not in", values, "resourceId");
            return (Criteria) this;
        }

        public Criteria andResourceIdBetween(Integer value1, Integer value2) {
            addCriterion("resource_id between", value1, value2, "resourceId");
            return (Criteria) this;
        }

        public Criteria andResourceIdNotBetween(Integer value1, Integer value2) {
            addCriterion("resource_id not between", value1, value2, "resourceId");
            return (Criteria) this;
        }

        public Criteria andAuthorIsNull() {
            addCriterion("author is null");
            return (Criteria) this;
        }

        public Criteria andAuthorIsNotNull() {
            addCriterion("author is not null");
            return (Criteria) this;
        }

        public Criteria andAuthorEqualTo(String value) {
            addCriterion("author =", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotEqualTo(String value) {
            addCriterion("author <>", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorGreaterThan(String value) {
            addCriterion("author >", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorGreaterThanOrEqualTo(String value) {
            addCriterion("author >=", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLessThan(String value) {
            addCriterion("author <", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLessThanOrEqualTo(String value) {
            addCriterion("author <=", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLike(String value) {
            addCriterion("author like", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotLike(String value) {
            addCriterion("author not like", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorIn(List<String> values) {
            addCriterion("author in", values, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotIn(List<String> values) {
            addCriterion("author not in", values, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorBetween(String value1, String value2) {
            addCriterion("author between", value1, value2, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotBetween(String value1, String value2) {
            addCriterion("author not between", value1, value2, "author");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNull() {
            addCriterion("creator is null");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNotNull() {
            addCriterion("creator is not null");
            return (Criteria) this;
        }

        public Criteria andCreatorEqualTo(String value) {
            addCriterion("creator =", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotEqualTo(String value) {
            addCriterion("creator <>", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThan(String value) {
            addCriterion("creator >", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThanOrEqualTo(String value) {
            addCriterion("creator >=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThan(String value) {
            addCriterion("creator <", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThanOrEqualTo(String value) {
            addCriterion("creator <=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLike(String value) {
            addCriterion("creator like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotLike(String value) {
            addCriterion("creator not like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorIn(List<String> values) {
            addCriterion("creator in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotIn(List<String> values) {
            addCriterion("creator not in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorBetween(String value1, String value2) {
            addCriterion("creator between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotBetween(String value1, String value2) {
            addCriterion("creator not between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNull() {
            addCriterion("is_delete is null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNotNull() {
            addCriterion("is_delete is not null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteEqualTo(Integer value) {
            addCriterion("is_delete =", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotEqualTo(Integer value) {
            addCriterion("is_delete <>", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThan(Integer value) {
            addCriterion("is_delete >", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_delete >=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThan(Integer value) {
            addCriterion("is_delete <", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThanOrEqualTo(Integer value) {
            addCriterion("is_delete <=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIn(List<Integer> values) {
            addCriterion("is_delete in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotIn(List<Integer> values) {
            addCriterion("is_delete not in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteBetween(Integer value1, Integer value2) {
            addCriterion("is_delete between", value1, value2, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotBetween(Integer value1, Integer value2) {
            addCriterion("is_delete not between", value1, value2, "isDelete");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(LocalDateTime value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(LocalDateTime value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(LocalDateTime value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(LocalDateTime value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<LocalDateTime> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<LocalDateTime> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andDatetimeLastchangeIsNull() {
            addCriterion("datetime_lastchange is null");
            return (Criteria) this;
        }

        public Criteria andDatetimeLastchangeIsNotNull() {
            addCriterion("datetime_lastchange is not null");
            return (Criteria) this;
        }

        public Criteria andDatetimeLastchangeEqualTo(LocalDateTime value) {
            addCriterion("datetime_lastchange =", value, "datetimeLastchange");
            return (Criteria) this;
        }

        public Criteria andDatetimeLastchangeNotEqualTo(LocalDateTime value) {
            addCriterion("datetime_lastchange <>", value, "datetimeLastchange");
            return (Criteria) this;
        }

        public Criteria andDatetimeLastchangeGreaterThan(LocalDateTime value) {
            addCriterion("datetime_lastchange >", value, "datetimeLastchange");
            return (Criteria) this;
        }

        public Criteria andDatetimeLastchangeGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("datetime_lastchange >=", value, "datetimeLastchange");
            return (Criteria) this;
        }

        public Criteria andDatetimeLastchangeLessThan(LocalDateTime value) {
            addCriterion("datetime_lastchange <", value, "datetimeLastchange");
            return (Criteria) this;
        }

        public Criteria andDatetimeLastchangeLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("datetime_lastchange <=", value, "datetimeLastchange");
            return (Criteria) this;
        }

        public Criteria andDatetimeLastchangeIn(List<LocalDateTime> values) {
            addCriterion("datetime_lastchange in", values, "datetimeLastchange");
            return (Criteria) this;
        }

        public Criteria andDatetimeLastchangeNotIn(List<LocalDateTime> values) {
            addCriterion("datetime_lastchange not in", values, "datetimeLastchange");
            return (Criteria) this;
        }

        public Criteria andDatetimeLastchangeBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("datetime_lastchange between", value1, value2, "datetimeLastchange");
            return (Criteria) this;
        }

        public Criteria andDatetimeLastchangeNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("datetime_lastchange not between", value1, value2, "datetimeLastchange");
            return (Criteria) this;
        }

        public Criteria andDocumentNameLikeInsensitive(String value) {
            addCriterion("upper(document_name) like", value.toUpperCase(), "documentName");
            return (Criteria) this;
        }

        public Criteria andFileVersionLikeInsensitive(String value) {
            addCriterion("upper(file_version) like", value.toUpperCase(), "fileVersion");
            return (Criteria) this;
        }

        public Criteria andCategoryLikeInsensitive(String value) {
            addCriterion("upper(category) like", value.toUpperCase(), "category");
            return (Criteria) this;
        }

        public Criteria andProjectLikeInsensitive(String value) {
            addCriterion("upper(project) like", value.toUpperCase(), "project");
            return (Criteria) this;
        }

        public Criteria andExpireDateLikeInsensitive(String value) {
            addCriterion("upper(expire_date) like", value.toUpperCase(), "expireDate");
            return (Criteria) this;
        }

        public Criteria andAuthorLikeInsensitive(String value) {
            addCriterion("upper(author) like", value.toUpperCase(), "author");
            return (Criteria) this;
        }

        public Criteria andCreatorLikeInsensitive(String value) {
            addCriterion("upper(creator) like", value.toUpperCase(), "creator");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}