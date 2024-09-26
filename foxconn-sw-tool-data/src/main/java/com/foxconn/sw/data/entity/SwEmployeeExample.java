package com.foxconn.sw.data.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SwEmployeeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SwEmployeeExample() {
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

        public Criteria andEmployeeNoIsNull() {
            addCriterion("employee_no is null");
            return (Criteria) this;
        }

        public Criteria andEmployeeNoIsNotNull() {
            addCriterion("employee_no is not null");
            return (Criteria) this;
        }

        public Criteria andEmployeeNoEqualTo(String value) {
            addCriterion("employee_no =", value, "employeeNo");
            return (Criteria) this;
        }

        public Criteria andEmployeeNoNotEqualTo(String value) {
            addCriterion("employee_no <>", value, "employeeNo");
            return (Criteria) this;
        }

        public Criteria andEmployeeNoGreaterThan(String value) {
            addCriterion("employee_no >", value, "employeeNo");
            return (Criteria) this;
        }

        public Criteria andEmployeeNoGreaterThanOrEqualTo(String value) {
            addCriterion("employee_no >=", value, "employeeNo");
            return (Criteria) this;
        }

        public Criteria andEmployeeNoLessThan(String value) {
            addCriterion("employee_no <", value, "employeeNo");
            return (Criteria) this;
        }

        public Criteria andEmployeeNoLessThanOrEqualTo(String value) {
            addCriterion("employee_no <=", value, "employeeNo");
            return (Criteria) this;
        }

        public Criteria andEmployeeNoLike(String value) {
            addCriterion("employee_no like", value, "employeeNo");
            return (Criteria) this;
        }

        public Criteria andEmployeeNoNotLike(String value) {
            addCriterion("employee_no not like", value, "employeeNo");
            return (Criteria) this;
        }

        public Criteria andEmployeeNoIn(List<String> values) {
            addCriterion("employee_no in", values, "employeeNo");
            return (Criteria) this;
        }

        public Criteria andEmployeeNoNotIn(List<String> values) {
            addCriterion("employee_no not in", values, "employeeNo");
            return (Criteria) this;
        }

        public Criteria andEmployeeNoBetween(String value1, String value2) {
            addCriterion("employee_no between", value1, value2, "employeeNo");
            return (Criteria) this;
        }

        public Criteria andEmployeeNoNotBetween(String value1, String value2) {
            addCriterion("employee_no not between", value1, value2, "employeeNo");
            return (Criteria) this;
        }

        public Criteria andAssistantIsNull() {
            addCriterion("assistant is null");
            return (Criteria) this;
        }

        public Criteria andAssistantIsNotNull() {
            addCriterion("assistant is not null");
            return (Criteria) this;
        }

        public Criteria andAssistantEqualTo(String value) {
            addCriterion("assistant =", value, "assistant");
            return (Criteria) this;
        }

        public Criteria andAssistantNotEqualTo(String value) {
            addCriterion("assistant <>", value, "assistant");
            return (Criteria) this;
        }

        public Criteria andAssistantGreaterThan(String value) {
            addCriterion("assistant >", value, "assistant");
            return (Criteria) this;
        }

        public Criteria andAssistantGreaterThanOrEqualTo(String value) {
            addCriterion("assistant >=", value, "assistant");
            return (Criteria) this;
        }

        public Criteria andAssistantLessThan(String value) {
            addCriterion("assistant <", value, "assistant");
            return (Criteria) this;
        }

        public Criteria andAssistantLessThanOrEqualTo(String value) {
            addCriterion("assistant <=", value, "assistant");
            return (Criteria) this;
        }

        public Criteria andAssistantLike(String value) {
            addCriterion("assistant like", value, "assistant");
            return (Criteria) this;
        }

        public Criteria andAssistantNotLike(String value) {
            addCriterion("assistant not like", value, "assistant");
            return (Criteria) this;
        }

        public Criteria andAssistantIn(List<String> values) {
            addCriterion("assistant in", values, "assistant");
            return (Criteria) this;
        }

        public Criteria andAssistantNotIn(List<String> values) {
            addCriterion("assistant not in", values, "assistant");
            return (Criteria) this;
        }

        public Criteria andAssistantBetween(String value1, String value2) {
            addCriterion("assistant between", value1, value2, "assistant");
            return (Criteria) this;
        }

        public Criteria andAssistantNotBetween(String value1, String value2) {
            addCriterion("assistant not between", value1, value2, "assistant");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andFirstNameIsNull() {
            addCriterion("first_name is null");
            return (Criteria) this;
        }

        public Criteria andFirstNameIsNotNull() {
            addCriterion("first_name is not null");
            return (Criteria) this;
        }

        public Criteria andFirstNameEqualTo(String value) {
            addCriterion("first_name =", value, "firstName");
            return (Criteria) this;
        }

        public Criteria andFirstNameNotEqualTo(String value) {
            addCriterion("first_name <>", value, "firstName");
            return (Criteria) this;
        }

        public Criteria andFirstNameGreaterThan(String value) {
            addCriterion("first_name >", value, "firstName");
            return (Criteria) this;
        }

        public Criteria andFirstNameGreaterThanOrEqualTo(String value) {
            addCriterion("first_name >=", value, "firstName");
            return (Criteria) this;
        }

        public Criteria andFirstNameLessThan(String value) {
            addCriterion("first_name <", value, "firstName");
            return (Criteria) this;
        }

        public Criteria andFirstNameLessThanOrEqualTo(String value) {
            addCriterion("first_name <=", value, "firstName");
            return (Criteria) this;
        }

        public Criteria andFirstNameLike(String value) {
            addCriterion("first_name like", value, "firstName");
            return (Criteria) this;
        }

        public Criteria andFirstNameNotLike(String value) {
            addCriterion("first_name not like", value, "firstName");
            return (Criteria) this;
        }

        public Criteria andFirstNameIn(List<String> values) {
            addCriterion("first_name in", values, "firstName");
            return (Criteria) this;
        }

        public Criteria andFirstNameNotIn(List<String> values) {
            addCriterion("first_name not in", values, "firstName");
            return (Criteria) this;
        }

        public Criteria andFirstNameBetween(String value1, String value2) {
            addCriterion("first_name between", value1, value2, "firstName");
            return (Criteria) this;
        }

        public Criteria andFirstNameNotBetween(String value1, String value2) {
            addCriterion("first_name not between", value1, value2, "firstName");
            return (Criteria) this;
        }

        public Criteria andLastNameIsNull() {
            addCriterion("last_name is null");
            return (Criteria) this;
        }

        public Criteria andLastNameIsNotNull() {
            addCriterion("last_name is not null");
            return (Criteria) this;
        }

        public Criteria andLastNameEqualTo(String value) {
            addCriterion("last_name =", value, "lastName");
            return (Criteria) this;
        }

        public Criteria andLastNameNotEqualTo(String value) {
            addCriterion("last_name <>", value, "lastName");
            return (Criteria) this;
        }

        public Criteria andLastNameGreaterThan(String value) {
            addCriterion("last_name >", value, "lastName");
            return (Criteria) this;
        }

        public Criteria andLastNameGreaterThanOrEqualTo(String value) {
            addCriterion("last_name >=", value, "lastName");
            return (Criteria) this;
        }

        public Criteria andLastNameLessThan(String value) {
            addCriterion("last_name <", value, "lastName");
            return (Criteria) this;
        }

        public Criteria andLastNameLessThanOrEqualTo(String value) {
            addCriterion("last_name <=", value, "lastName");
            return (Criteria) this;
        }

        public Criteria andLastNameLike(String value) {
            addCriterion("last_name like", value, "lastName");
            return (Criteria) this;
        }

        public Criteria andLastNameNotLike(String value) {
            addCriterion("last_name not like", value, "lastName");
            return (Criteria) this;
        }

        public Criteria andLastNameIn(List<String> values) {
            addCriterion("last_name in", values, "lastName");
            return (Criteria) this;
        }

        public Criteria andLastNameNotIn(List<String> values) {
            addCriterion("last_name not in", values, "lastName");
            return (Criteria) this;
        }

        public Criteria andLastNameBetween(String value1, String value2) {
            addCriterion("last_name between", value1, value2, "lastName");
            return (Criteria) this;
        }

        public Criteria andLastNameNotBetween(String value1, String value2) {
            addCriterion("last_name not between", value1, value2, "lastName");
            return (Criteria) this;
        }

        public Criteria andGenderIsNull() {
            addCriterion("gender is null");
            return (Criteria) this;
        }

        public Criteria andGenderIsNotNull() {
            addCriterion("gender is not null");
            return (Criteria) this;
        }

        public Criteria andGenderEqualTo(String value) {
            addCriterion("gender =", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotEqualTo(String value) {
            addCriterion("gender <>", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderGreaterThan(String value) {
            addCriterion("gender >", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderGreaterThanOrEqualTo(String value) {
            addCriterion("gender >=", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderLessThan(String value) {
            addCriterion("gender <", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderLessThanOrEqualTo(String value) {
            addCriterion("gender <=", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderLike(String value) {
            addCriterion("gender like", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotLike(String value) {
            addCriterion("gender not like", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderIn(List<String> values) {
            addCriterion("gender in", values, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotIn(List<String> values) {
            addCriterion("gender not in", values, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderBetween(String value1, String value2) {
            addCriterion("gender between", value1, value2, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotBetween(String value1, String value2) {
            addCriterion("gender not between", value1, value2, "gender");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdIsNull() {
            addCriterion("department_id is null");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdIsNotNull() {
            addCriterion("department_id is not null");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdEqualTo(Integer value) {
            addCriterion("department_id =", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdNotEqualTo(Integer value) {
            addCriterion("department_id <>", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdGreaterThan(Integer value) {
            addCriterion("department_id >", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("department_id >=", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdLessThan(Integer value) {
            addCriterion("department_id <", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdLessThanOrEqualTo(Integer value) {
            addCriterion("department_id <=", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdIn(List<Integer> values) {
            addCriterion("department_id in", values, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdNotIn(List<Integer> values) {
            addCriterion("department_id not in", values, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdBetween(Integer value1, Integer value2) {
            addCriterion("department_id between", value1, value2, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("department_id not between", value1, value2, "departmentId");
            return (Criteria) this;
        }

        public Criteria andPostIdIsNull() {
            addCriterion("post_id is null");
            return (Criteria) this;
        }

        public Criteria andPostIdIsNotNull() {
            addCriterion("post_id is not null");
            return (Criteria) this;
        }

        public Criteria andPostIdEqualTo(Integer value) {
            addCriterion("post_id =", value, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdNotEqualTo(Integer value) {
            addCriterion("post_id <>", value, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdGreaterThan(Integer value) {
            addCriterion("post_id >", value, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("post_id >=", value, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdLessThan(Integer value) {
            addCriterion("post_id <", value, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdLessThanOrEqualTo(Integer value) {
            addCriterion("post_id <=", value, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdIn(List<Integer> values) {
            addCriterion("post_id in", values, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdNotIn(List<Integer> values) {
            addCriterion("post_id not in", values, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdBetween(Integer value1, Integer value2) {
            addCriterion("post_id between", value1, value2, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdNotBetween(Integer value1, Integer value2) {
            addCriterion("post_id not between", value1, value2, "postId");
            return (Criteria) this;
        }

        public Criteria andInnerEmailIsNull() {
            addCriterion("inner_email is null");
            return (Criteria) this;
        }

        public Criteria andInnerEmailIsNotNull() {
            addCriterion("inner_email is not null");
            return (Criteria) this;
        }

        public Criteria andInnerEmailEqualTo(String value) {
            addCriterion("inner_email =", value, "innerEmail");
            return (Criteria) this;
        }

        public Criteria andInnerEmailNotEqualTo(String value) {
            addCriterion("inner_email <>", value, "innerEmail");
            return (Criteria) this;
        }

        public Criteria andInnerEmailGreaterThan(String value) {
            addCriterion("inner_email >", value, "innerEmail");
            return (Criteria) this;
        }

        public Criteria andInnerEmailGreaterThanOrEqualTo(String value) {
            addCriterion("inner_email >=", value, "innerEmail");
            return (Criteria) this;
        }

        public Criteria andInnerEmailLessThan(String value) {
            addCriterion("inner_email <", value, "innerEmail");
            return (Criteria) this;
        }

        public Criteria andInnerEmailLessThanOrEqualTo(String value) {
            addCriterion("inner_email <=", value, "innerEmail");
            return (Criteria) this;
        }

        public Criteria andInnerEmailLike(String value) {
            addCriterion("inner_email like", value, "innerEmail");
            return (Criteria) this;
        }

        public Criteria andInnerEmailNotLike(String value) {
            addCriterion("inner_email not like", value, "innerEmail");
            return (Criteria) this;
        }

        public Criteria andInnerEmailIn(List<String> values) {
            addCriterion("inner_email in", values, "innerEmail");
            return (Criteria) this;
        }

        public Criteria andInnerEmailNotIn(List<String> values) {
            addCriterion("inner_email not in", values, "innerEmail");
            return (Criteria) this;
        }

        public Criteria andInnerEmailBetween(String value1, String value2) {
            addCriterion("inner_email between", value1, value2, "innerEmail");
            return (Criteria) this;
        }

        public Criteria andInnerEmailNotBetween(String value1, String value2) {
            addCriterion("inner_email not between", value1, value2, "innerEmail");
            return (Criteria) this;
        }

        public Criteria andOuterMailIsNull() {
            addCriterion("outer_mail is null");
            return (Criteria) this;
        }

        public Criteria andOuterMailIsNotNull() {
            addCriterion("outer_mail is not null");
            return (Criteria) this;
        }

        public Criteria andOuterMailEqualTo(String value) {
            addCriterion("outer_mail =", value, "outerMail");
            return (Criteria) this;
        }

        public Criteria andOuterMailNotEqualTo(String value) {
            addCriterion("outer_mail <>", value, "outerMail");
            return (Criteria) this;
        }

        public Criteria andOuterMailGreaterThan(String value) {
            addCriterion("outer_mail >", value, "outerMail");
            return (Criteria) this;
        }

        public Criteria andOuterMailGreaterThanOrEqualTo(String value) {
            addCriterion("outer_mail >=", value, "outerMail");
            return (Criteria) this;
        }

        public Criteria andOuterMailLessThan(String value) {
            addCriterion("outer_mail <", value, "outerMail");
            return (Criteria) this;
        }

        public Criteria andOuterMailLessThanOrEqualTo(String value) {
            addCriterion("outer_mail <=", value, "outerMail");
            return (Criteria) this;
        }

        public Criteria andOuterMailLike(String value) {
            addCriterion("outer_mail like", value, "outerMail");
            return (Criteria) this;
        }

        public Criteria andOuterMailNotLike(String value) {
            addCriterion("outer_mail not like", value, "outerMail");
            return (Criteria) this;
        }

        public Criteria andOuterMailIn(List<String> values) {
            addCriterion("outer_mail in", values, "outerMail");
            return (Criteria) this;
        }

        public Criteria andOuterMailNotIn(List<String> values) {
            addCriterion("outer_mail not in", values, "outerMail");
            return (Criteria) this;
        }

        public Criteria andOuterMailBetween(String value1, String value2) {
            addCriterion("outer_mail between", value1, value2, "outerMail");
            return (Criteria) this;
        }

        public Criteria andOuterMailNotBetween(String value1, String value2) {
            addCriterion("outer_mail not between", value1, value2, "outerMail");
            return (Criteria) this;
        }

        public Criteria andLandLineIsNull() {
            addCriterion("land_line is null");
            return (Criteria) this;
        }

        public Criteria andLandLineIsNotNull() {
            addCriterion("land_line is not null");
            return (Criteria) this;
        }

        public Criteria andLandLineEqualTo(String value) {
            addCriterion("land_line =", value, "landLine");
            return (Criteria) this;
        }

        public Criteria andLandLineNotEqualTo(String value) {
            addCriterion("land_line <>", value, "landLine");
            return (Criteria) this;
        }

        public Criteria andLandLineGreaterThan(String value) {
            addCriterion("land_line >", value, "landLine");
            return (Criteria) this;
        }

        public Criteria andLandLineGreaterThanOrEqualTo(String value) {
            addCriterion("land_line >=", value, "landLine");
            return (Criteria) this;
        }

        public Criteria andLandLineLessThan(String value) {
            addCriterion("land_line <", value, "landLine");
            return (Criteria) this;
        }

        public Criteria andLandLineLessThanOrEqualTo(String value) {
            addCriterion("land_line <=", value, "landLine");
            return (Criteria) this;
        }

        public Criteria andLandLineLike(String value) {
            addCriterion("land_line like", value, "landLine");
            return (Criteria) this;
        }

        public Criteria andLandLineNotLike(String value) {
            addCriterion("land_line not like", value, "landLine");
            return (Criteria) this;
        }

        public Criteria andLandLineIn(List<String> values) {
            addCriterion("land_line in", values, "landLine");
            return (Criteria) this;
        }

        public Criteria andLandLineNotIn(List<String> values) {
            addCriterion("land_line not in", values, "landLine");
            return (Criteria) this;
        }

        public Criteria andLandLineBetween(String value1, String value2) {
            addCriterion("land_line between", value1, value2, "landLine");
            return (Criteria) this;
        }

        public Criteria andLandLineNotBetween(String value1, String value2) {
            addCriterion("land_line not between", value1, value2, "landLine");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberIsNull() {
            addCriterion("phone_number is null");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberIsNotNull() {
            addCriterion("phone_number is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberEqualTo(String value) {
            addCriterion("phone_number =", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberNotEqualTo(String value) {
            addCriterion("phone_number <>", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberGreaterThan(String value) {
            addCriterion("phone_number >", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberGreaterThanOrEqualTo(String value) {
            addCriterion("phone_number >=", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberLessThan(String value) {
            addCriterion("phone_number <", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberLessThanOrEqualTo(String value) {
            addCriterion("phone_number <=", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberLike(String value) {
            addCriterion("phone_number like", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberNotLike(String value) {
            addCriterion("phone_number not like", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberIn(List<String> values) {
            addCriterion("phone_number in", values, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberNotIn(List<String> values) {
            addCriterion("phone_number not in", values, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberBetween(String value1, String value2) {
            addCriterion("phone_number between", value1, value2, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberNotBetween(String value1, String value2) {
            addCriterion("phone_number not between", value1, value2, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andHireDateIsNull() {
            addCriterion("hire_date is null");
            return (Criteria) this;
        }

        public Criteria andHireDateIsNotNull() {
            addCriterion("hire_date is not null");
            return (Criteria) this;
        }

        public Criteria andHireDateEqualTo(String value) {
            addCriterion("hire_date =", value, "hireDate");
            return (Criteria) this;
        }

        public Criteria andHireDateNotEqualTo(String value) {
            addCriterion("hire_date <>", value, "hireDate");
            return (Criteria) this;
        }

        public Criteria andHireDateGreaterThan(String value) {
            addCriterion("hire_date >", value, "hireDate");
            return (Criteria) this;
        }

        public Criteria andHireDateGreaterThanOrEqualTo(String value) {
            addCriterion("hire_date >=", value, "hireDate");
            return (Criteria) this;
        }

        public Criteria andHireDateLessThan(String value) {
            addCriterion("hire_date <", value, "hireDate");
            return (Criteria) this;
        }

        public Criteria andHireDateLessThanOrEqualTo(String value) {
            addCriterion("hire_date <=", value, "hireDate");
            return (Criteria) this;
        }

        public Criteria andHireDateLike(String value) {
            addCriterion("hire_date like", value, "hireDate");
            return (Criteria) this;
        }

        public Criteria andHireDateNotLike(String value) {
            addCriterion("hire_date not like", value, "hireDate");
            return (Criteria) this;
        }

        public Criteria andHireDateIn(List<String> values) {
            addCriterion("hire_date in", values, "hireDate");
            return (Criteria) this;
        }

        public Criteria andHireDateNotIn(List<String> values) {
            addCriterion("hire_date not in", values, "hireDate");
            return (Criteria) this;
        }

        public Criteria andHireDateBetween(String value1, String value2) {
            addCriterion("hire_date between", value1, value2, "hireDate");
            return (Criteria) this;
        }

        public Criteria andHireDateNotBetween(String value1, String value2) {
            addCriterion("hire_date not between", value1, value2, "hireDate");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andOuterWorkYearsIsNull() {
            addCriterion("outer_work_years is null");
            return (Criteria) this;
        }

        public Criteria andOuterWorkYearsIsNotNull() {
            addCriterion("outer_work_years is not null");
            return (Criteria) this;
        }

        public Criteria andOuterWorkYearsEqualTo(Integer value) {
            addCriterion("outer_work_years =", value, "outerWorkYears");
            return (Criteria) this;
        }

        public Criteria andOuterWorkYearsNotEqualTo(Integer value) {
            addCriterion("outer_work_years <>", value, "outerWorkYears");
            return (Criteria) this;
        }

        public Criteria andOuterWorkYearsGreaterThan(Integer value) {
            addCriterion("outer_work_years >", value, "outerWorkYears");
            return (Criteria) this;
        }

        public Criteria andOuterWorkYearsGreaterThanOrEqualTo(Integer value) {
            addCriterion("outer_work_years >=", value, "outerWorkYears");
            return (Criteria) this;
        }

        public Criteria andOuterWorkYearsLessThan(Integer value) {
            addCriterion("outer_work_years <", value, "outerWorkYears");
            return (Criteria) this;
        }

        public Criteria andOuterWorkYearsLessThanOrEqualTo(Integer value) {
            addCriterion("outer_work_years <=", value, "outerWorkYears");
            return (Criteria) this;
        }

        public Criteria andOuterWorkYearsIn(List<Integer> values) {
            addCriterion("outer_work_years in", values, "outerWorkYears");
            return (Criteria) this;
        }

        public Criteria andOuterWorkYearsNotIn(List<Integer> values) {
            addCriterion("outer_work_years not in", values, "outerWorkYears");
            return (Criteria) this;
        }

        public Criteria andOuterWorkYearsBetween(Integer value1, Integer value2) {
            addCriterion("outer_work_years between", value1, value2, "outerWorkYears");
            return (Criteria) this;
        }

        public Criteria andOuterWorkYearsNotBetween(Integer value1, Integer value2) {
            addCriterion("outer_work_years not between", value1, value2, "outerWorkYears");
            return (Criteria) this;
        }

        public Criteria andOuterAbcYearsIsNull() {
            addCriterion("outer_abc_years is null");
            return (Criteria) this;
        }

        public Criteria andOuterAbcYearsIsNotNull() {
            addCriterion("outer_abc_years is not null");
            return (Criteria) this;
        }

        public Criteria andOuterAbcYearsEqualTo(Integer value) {
            addCriterion("outer_abc_years =", value, "outerAbcYears");
            return (Criteria) this;
        }

        public Criteria andOuterAbcYearsNotEqualTo(Integer value) {
            addCriterion("outer_abc_years <>", value, "outerAbcYears");
            return (Criteria) this;
        }

        public Criteria andOuterAbcYearsGreaterThan(Integer value) {
            addCriterion("outer_abc_years >", value, "outerAbcYears");
            return (Criteria) this;
        }

        public Criteria andOuterAbcYearsGreaterThanOrEqualTo(Integer value) {
            addCriterion("outer_abc_years >=", value, "outerAbcYears");
            return (Criteria) this;
        }

        public Criteria andOuterAbcYearsLessThan(Integer value) {
            addCriterion("outer_abc_years <", value, "outerAbcYears");
            return (Criteria) this;
        }

        public Criteria andOuterAbcYearsLessThanOrEqualTo(Integer value) {
            addCriterion("outer_abc_years <=", value, "outerAbcYears");
            return (Criteria) this;
        }

        public Criteria andOuterAbcYearsIn(List<Integer> values) {
            addCriterion("outer_abc_years in", values, "outerAbcYears");
            return (Criteria) this;
        }

        public Criteria andOuterAbcYearsNotIn(List<Integer> values) {
            addCriterion("outer_abc_years not in", values, "outerAbcYears");
            return (Criteria) this;
        }

        public Criteria andOuterAbcYearsBetween(Integer value1, Integer value2) {
            addCriterion("outer_abc_years between", value1, value2, "outerAbcYears");
            return (Criteria) this;
        }

        public Criteria andOuterAbcYearsNotBetween(Integer value1, Integer value2) {
            addCriterion("outer_abc_years not between", value1, value2, "outerAbcYears");
            return (Criteria) this;
        }

        public Criteria andManagerLevelIsNull() {
            addCriterion("manager_level is null");
            return (Criteria) this;
        }

        public Criteria andManagerLevelIsNotNull() {
            addCriterion("manager_level is not null");
            return (Criteria) this;
        }

        public Criteria andManagerLevelEqualTo(Integer value) {
            addCriterion("manager_level =", value, "managerLevel");
            return (Criteria) this;
        }

        public Criteria andManagerLevelNotEqualTo(Integer value) {
            addCriterion("manager_level <>", value, "managerLevel");
            return (Criteria) this;
        }

        public Criteria andManagerLevelGreaterThan(Integer value) {
            addCriterion("manager_level >", value, "managerLevel");
            return (Criteria) this;
        }

        public Criteria andManagerLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("manager_level >=", value, "managerLevel");
            return (Criteria) this;
        }

        public Criteria andManagerLevelLessThan(Integer value) {
            addCriterion("manager_level <", value, "managerLevel");
            return (Criteria) this;
        }

        public Criteria andManagerLevelLessThanOrEqualTo(Integer value) {
            addCriterion("manager_level <=", value, "managerLevel");
            return (Criteria) this;
        }

        public Criteria andManagerLevelIn(List<Integer> values) {
            addCriterion("manager_level in", values, "managerLevel");
            return (Criteria) this;
        }

        public Criteria andManagerLevelNotIn(List<Integer> values) {
            addCriterion("manager_level not in", values, "managerLevel");
            return (Criteria) this;
        }

        public Criteria andManagerLevelBetween(Integer value1, Integer value2) {
            addCriterion("manager_level between", value1, value2, "managerLevel");
            return (Criteria) this;
        }

        public Criteria andManagerLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("manager_level not between", value1, value2, "managerLevel");
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

        public Criteria andEmployeeNoLikeInsensitive(String value) {
            addCriterion("upper(employee_no) like", value.toUpperCase(), "employeeNo");
            return (Criteria) this;
        }

        public Criteria andAssistantLikeInsensitive(String value) {
            addCriterion("upper(assistant) like", value.toUpperCase(), "assistant");
            return (Criteria) this;
        }

        public Criteria andNameLikeInsensitive(String value) {
            addCriterion("upper(name) like", value.toUpperCase(), "name");
            return (Criteria) this;
        }

        public Criteria andFirstNameLikeInsensitive(String value) {
            addCriterion("upper(first_name) like", value.toUpperCase(), "firstName");
            return (Criteria) this;
        }

        public Criteria andLastNameLikeInsensitive(String value) {
            addCriterion("upper(last_name) like", value.toUpperCase(), "lastName");
            return (Criteria) this;
        }

        public Criteria andGenderLikeInsensitive(String value) {
            addCriterion("upper(gender) like", value.toUpperCase(), "gender");
            return (Criteria) this;
        }

        public Criteria andInnerEmailLikeInsensitive(String value) {
            addCriterion("upper(inner_email) like", value.toUpperCase(), "innerEmail");
            return (Criteria) this;
        }

        public Criteria andOuterMailLikeInsensitive(String value) {
            addCriterion("upper(outer_mail) like", value.toUpperCase(), "outerMail");
            return (Criteria) this;
        }

        public Criteria andLandLineLikeInsensitive(String value) {
            addCriterion("upper(land_line) like", value.toUpperCase(), "landLine");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberLikeInsensitive(String value) {
            addCriterion("upper(phone_number) like", value.toUpperCase(), "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andHireDateLikeInsensitive(String value) {
            addCriterion("upper(hire_date) like", value.toUpperCase(), "hireDate");
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