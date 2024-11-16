package com.foxconn.sw.data.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ForumPostsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ForumPostsExample() {
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

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andAuthorNoIsNull() {
            addCriterion("author_no is null");
            return (Criteria) this;
        }

        public Criteria andAuthorNoIsNotNull() {
            addCriterion("author_no is not null");
            return (Criteria) this;
        }

        public Criteria andAuthorNoEqualTo(String value) {
            addCriterion("author_no =", value, "authorNo");
            return (Criteria) this;
        }

        public Criteria andAuthorNoNotEqualTo(String value) {
            addCriterion("author_no <>", value, "authorNo");
            return (Criteria) this;
        }

        public Criteria andAuthorNoGreaterThan(String value) {
            addCriterion("author_no >", value, "authorNo");
            return (Criteria) this;
        }

        public Criteria andAuthorNoGreaterThanOrEqualTo(String value) {
            addCriterion("author_no >=", value, "authorNo");
            return (Criteria) this;
        }

        public Criteria andAuthorNoLessThan(String value) {
            addCriterion("author_no <", value, "authorNo");
            return (Criteria) this;
        }

        public Criteria andAuthorNoLessThanOrEqualTo(String value) {
            addCriterion("author_no <=", value, "authorNo");
            return (Criteria) this;
        }

        public Criteria andAuthorNoLike(String value) {
            addCriterion("author_no like", value, "authorNo");
            return (Criteria) this;
        }

        public Criteria andAuthorNoNotLike(String value) {
            addCriterion("author_no not like", value, "authorNo");
            return (Criteria) this;
        }

        public Criteria andAuthorNoIn(List<String> values) {
            addCriterion("author_no in", values, "authorNo");
            return (Criteria) this;
        }

        public Criteria andAuthorNoNotIn(List<String> values) {
            addCriterion("author_no not in", values, "authorNo");
            return (Criteria) this;
        }

        public Criteria andAuthorNoBetween(String value1, String value2) {
            addCriterion("author_no between", value1, value2, "authorNo");
            return (Criteria) this;
        }

        public Criteria andAuthorNoNotBetween(String value1, String value2) {
            addCriterion("author_no not between", value1, value2, "authorNo");
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

        public Criteria andPurviewIsNull() {
            addCriterion("purview is null");
            return (Criteria) this;
        }

        public Criteria andPurviewIsNotNull() {
            addCriterion("purview is not null");
            return (Criteria) this;
        }

        public Criteria andPurviewEqualTo(Integer value) {
            addCriterion("purview =", value, "purview");
            return (Criteria) this;
        }

        public Criteria andPurviewNotEqualTo(Integer value) {
            addCriterion("purview <>", value, "purview");
            return (Criteria) this;
        }

        public Criteria andPurviewGreaterThan(Integer value) {
            addCriterion("purview >", value, "purview");
            return (Criteria) this;
        }

        public Criteria andPurviewGreaterThanOrEqualTo(Integer value) {
            addCriterion("purview >=", value, "purview");
            return (Criteria) this;
        }

        public Criteria andPurviewLessThan(Integer value) {
            addCriterion("purview <", value, "purview");
            return (Criteria) this;
        }

        public Criteria andPurviewLessThanOrEqualTo(Integer value) {
            addCriterion("purview <=", value, "purview");
            return (Criteria) this;
        }

        public Criteria andPurviewIn(List<Integer> values) {
            addCriterion("purview in", values, "purview");
            return (Criteria) this;
        }

        public Criteria andPurviewNotIn(List<Integer> values) {
            addCriterion("purview not in", values, "purview");
            return (Criteria) this;
        }

        public Criteria andPurviewBetween(Integer value1, Integer value2) {
            addCriterion("purview between", value1, value2, "purview");
            return (Criteria) this;
        }

        public Criteria andPurviewNotBetween(Integer value1, Integer value2) {
            addCriterion("purview not between", value1, value2, "purview");
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

        public Criteria andLastchangeDatetimeIsNull() {
            addCriterion("lastchange_datetime is null");
            return (Criteria) this;
        }

        public Criteria andLastchangeDatetimeIsNotNull() {
            addCriterion("lastchange_datetime is not null");
            return (Criteria) this;
        }

        public Criteria andLastchangeDatetimeEqualTo(LocalDateTime value) {
            addCriterion("lastchange_datetime =", value, "lastchangeDatetime");
            return (Criteria) this;
        }

        public Criteria andLastchangeDatetimeNotEqualTo(LocalDateTime value) {
            addCriterion("lastchange_datetime <>", value, "lastchangeDatetime");
            return (Criteria) this;
        }

        public Criteria andLastchangeDatetimeGreaterThan(LocalDateTime value) {
            addCriterion("lastchange_datetime >", value, "lastchangeDatetime");
            return (Criteria) this;
        }

        public Criteria andLastchangeDatetimeGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("lastchange_datetime >=", value, "lastchangeDatetime");
            return (Criteria) this;
        }

        public Criteria andLastchangeDatetimeLessThan(LocalDateTime value) {
            addCriterion("lastchange_datetime <", value, "lastchangeDatetime");
            return (Criteria) this;
        }

        public Criteria andLastchangeDatetimeLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("lastchange_datetime <=", value, "lastchangeDatetime");
            return (Criteria) this;
        }

        public Criteria andLastchangeDatetimeIn(List<LocalDateTime> values) {
            addCriterion("lastchange_datetime in", values, "lastchangeDatetime");
            return (Criteria) this;
        }

        public Criteria andLastchangeDatetimeNotIn(List<LocalDateTime> values) {
            addCriterion("lastchange_datetime not in", values, "lastchangeDatetime");
            return (Criteria) this;
        }

        public Criteria andLastchangeDatetimeBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("lastchange_datetime between", value1, value2, "lastchangeDatetime");
            return (Criteria) this;
        }

        public Criteria andLastchangeDatetimeNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("lastchange_datetime not between", value1, value2, "lastchangeDatetime");
            return (Criteria) this;
        }

        public Criteria andTitleLikeInsensitive(String value) {
            addCriterion("upper(title) like", value.toUpperCase(), "title");
            return (Criteria) this;
        }

        public Criteria andAuthorNoLikeInsensitive(String value) {
            addCriterion("upper(author_no) like", value.toUpperCase(), "authorNo");
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