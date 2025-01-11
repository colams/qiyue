package com.foxconn.sw.data.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SwChangeLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SwChangeLogExample() {
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

        public Criteria andReleaseNoteIsNull() {
            addCriterion("release_note is null");
            return (Criteria) this;
        }

        public Criteria andReleaseNoteIsNotNull() {
            addCriterion("release_note is not null");
            return (Criteria) this;
        }

        public Criteria andReleaseNoteEqualTo(String value) {
            addCriterion("release_note =", value, "releaseNote");
            return (Criteria) this;
        }

        public Criteria andReleaseNoteNotEqualTo(String value) {
            addCriterion("release_note <>", value, "releaseNote");
            return (Criteria) this;
        }

        public Criteria andReleaseNoteGreaterThan(String value) {
            addCriterion("release_note >", value, "releaseNote");
            return (Criteria) this;
        }

        public Criteria andReleaseNoteGreaterThanOrEqualTo(String value) {
            addCriterion("release_note >=", value, "releaseNote");
            return (Criteria) this;
        }

        public Criteria andReleaseNoteLessThan(String value) {
            addCriterion("release_note <", value, "releaseNote");
            return (Criteria) this;
        }

        public Criteria andReleaseNoteLessThanOrEqualTo(String value) {
            addCriterion("release_note <=", value, "releaseNote");
            return (Criteria) this;
        }

        public Criteria andReleaseNoteLike(String value) {
            addCriterion("release_note like", value, "releaseNote");
            return (Criteria) this;
        }

        public Criteria andReleaseNoteNotLike(String value) {
            addCriterion("release_note not like", value, "releaseNote");
            return (Criteria) this;
        }

        public Criteria andReleaseNoteIn(List<String> values) {
            addCriterion("release_note in", values, "releaseNote");
            return (Criteria) this;
        }

        public Criteria andReleaseNoteNotIn(List<String> values) {
            addCriterion("release_note not in", values, "releaseNote");
            return (Criteria) this;
        }

        public Criteria andReleaseNoteBetween(String value1, String value2) {
            addCriterion("release_note between", value1, value2, "releaseNote");
            return (Criteria) this;
        }

        public Criteria andReleaseNoteNotBetween(String value1, String value2) {
            addCriterion("release_note not between", value1, value2, "releaseNote");
            return (Criteria) this;
        }

        public Criteria andReleaseVersionIsNull() {
            addCriterion("release_version is null");
            return (Criteria) this;
        }

        public Criteria andReleaseVersionIsNotNull() {
            addCriterion("release_version is not null");
            return (Criteria) this;
        }

        public Criteria andReleaseVersionEqualTo(String value) {
            addCriterion("release_version =", value, "releaseVersion");
            return (Criteria) this;
        }

        public Criteria andReleaseVersionNotEqualTo(String value) {
            addCriterion("release_version <>", value, "releaseVersion");
            return (Criteria) this;
        }

        public Criteria andReleaseVersionGreaterThan(String value) {
            addCriterion("release_version >", value, "releaseVersion");
            return (Criteria) this;
        }

        public Criteria andReleaseVersionGreaterThanOrEqualTo(String value) {
            addCriterion("release_version >=", value, "releaseVersion");
            return (Criteria) this;
        }

        public Criteria andReleaseVersionLessThan(String value) {
            addCriterion("release_version <", value, "releaseVersion");
            return (Criteria) this;
        }

        public Criteria andReleaseVersionLessThanOrEqualTo(String value) {
            addCriterion("release_version <=", value, "releaseVersion");
            return (Criteria) this;
        }

        public Criteria andReleaseVersionLike(String value) {
            addCriterion("release_version like", value, "releaseVersion");
            return (Criteria) this;
        }

        public Criteria andReleaseVersionNotLike(String value) {
            addCriterion("release_version not like", value, "releaseVersion");
            return (Criteria) this;
        }

        public Criteria andReleaseVersionIn(List<String> values) {
            addCriterion("release_version in", values, "releaseVersion");
            return (Criteria) this;
        }

        public Criteria andReleaseVersionNotIn(List<String> values) {
            addCriterion("release_version not in", values, "releaseVersion");
            return (Criteria) this;
        }

        public Criteria andReleaseVersionBetween(String value1, String value2) {
            addCriterion("release_version between", value1, value2, "releaseVersion");
            return (Criteria) this;
        }

        public Criteria andReleaseVersionNotBetween(String value1, String value2) {
            addCriterion("release_version not between", value1, value2, "releaseVersion");
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

        public Criteria andOperatorIsNull() {
            addCriterion("operator is null");
            return (Criteria) this;
        }

        public Criteria andOperatorIsNotNull() {
            addCriterion("operator is not null");
            return (Criteria) this;
        }

        public Criteria andOperatorEqualTo(String value) {
            addCriterion("operator =", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotEqualTo(String value) {
            addCriterion("operator <>", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorGreaterThan(String value) {
            addCriterion("operator >", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorGreaterThanOrEqualTo(String value) {
            addCriterion("operator >=", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLessThan(String value) {
            addCriterion("operator <", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLessThanOrEqualTo(String value) {
            addCriterion("operator <=", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLike(String value) {
            addCriterion("operator like", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotLike(String value) {
            addCriterion("operator not like", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorIn(List<String> values) {
            addCriterion("operator in", values, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotIn(List<String> values) {
            addCriterion("operator not in", values, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorBetween(String value1, String value2) {
            addCriterion("operator between", value1, value2, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotBetween(String value1, String value2) {
            addCriterion("operator not between", value1, value2, "operator");
            return (Criteria) this;
        }

        public Criteria andLastUpdaterIsNull() {
            addCriterion("last_updater is null");
            return (Criteria) this;
        }

        public Criteria andLastUpdaterIsNotNull() {
            addCriterion("last_updater is not null");
            return (Criteria) this;
        }

        public Criteria andLastUpdaterEqualTo(String value) {
            addCriterion("last_updater =", value, "lastUpdater");
            return (Criteria) this;
        }

        public Criteria andLastUpdaterNotEqualTo(String value) {
            addCriterion("last_updater <>", value, "lastUpdater");
            return (Criteria) this;
        }

        public Criteria andLastUpdaterGreaterThan(String value) {
            addCriterion("last_updater >", value, "lastUpdater");
            return (Criteria) this;
        }

        public Criteria andLastUpdaterGreaterThanOrEqualTo(String value) {
            addCriterion("last_updater >=", value, "lastUpdater");
            return (Criteria) this;
        }

        public Criteria andLastUpdaterLessThan(String value) {
            addCriterion("last_updater <", value, "lastUpdater");
            return (Criteria) this;
        }

        public Criteria andLastUpdaterLessThanOrEqualTo(String value) {
            addCriterion("last_updater <=", value, "lastUpdater");
            return (Criteria) this;
        }

        public Criteria andLastUpdaterLike(String value) {
            addCriterion("last_updater like", value, "lastUpdater");
            return (Criteria) this;
        }

        public Criteria andLastUpdaterNotLike(String value) {
            addCriterion("last_updater not like", value, "lastUpdater");
            return (Criteria) this;
        }

        public Criteria andLastUpdaterIn(List<String> values) {
            addCriterion("last_updater in", values, "lastUpdater");
            return (Criteria) this;
        }

        public Criteria andLastUpdaterNotIn(List<String> values) {
            addCriterion("last_updater not in", values, "lastUpdater");
            return (Criteria) this;
        }

        public Criteria andLastUpdaterBetween(String value1, String value2) {
            addCriterion("last_updater between", value1, value2, "lastUpdater");
            return (Criteria) this;
        }

        public Criteria andLastUpdaterNotBetween(String value1, String value2) {
            addCriterion("last_updater not between", value1, value2, "lastUpdater");
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

        public Criteria andReleaseNoteLikeInsensitive(String value) {
            addCriterion("upper(release_note) like", value.toUpperCase(), "releaseNote");
            return (Criteria) this;
        }

        public Criteria andReleaseVersionLikeInsensitive(String value) {
            addCriterion("upper(release_version) like", value.toUpperCase(), "releaseVersion");
            return (Criteria) this;
        }

        public Criteria andOperatorLikeInsensitive(String value) {
            addCriterion("upper(operator) like", value.toUpperCase(), "operator");
            return (Criteria) this;
        }

        public Criteria andLastUpdaterLikeInsensitive(String value) {
            addCriterion("upper(last_updater) like", value.toUpperCase(), "lastUpdater");
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