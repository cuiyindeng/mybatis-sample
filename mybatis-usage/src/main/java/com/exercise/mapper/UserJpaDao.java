package com.exercise.mapper;

import com.exercise.model.*;
import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserJpaDao {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    public List<Users> findAll() {
        QUsers a = QUsers.users;
        QOrders b = QOrders.orders;
        List<Users> usersList = jpaQueryFactory.from(a, b)
//                .leftJoin(b)
                .where(a.id.eq(b.userId))
                .transform(GroupBy.groupBy(a.id).list(Projections.bean(Users.class, a.id, a.age, a.name,
                        GroupBy.list(Projections.bean(Orders.class, b.userId, b.money)).as("ordersList"))));

        return usersList;
    }
}
