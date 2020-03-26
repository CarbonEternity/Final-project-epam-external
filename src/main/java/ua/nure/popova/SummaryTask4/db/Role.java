package ua.nure.popova.SummaryTask4.db;

import ua.nure.popova.SummaryTask4.db.entity.Entity;

public enum Role {
    ADMIN, CLIENT;

    // 0 - admin
    // 1 - enrollee

    public static Role getRole(Entity somebody) {
        int roleId = somebody.getRoleId();
        System.out.println(roleId);
        return Role.values()[roleId];
    }

    public String getName() {
        return name().toLowerCase();
    }

}
