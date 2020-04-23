package ua.nure.popova.SummaryTask4.db;

import ua.nure.popova.SummaryTask4.db.entity.Enrollee;

public enum AccessStatus {
     LOCKED, UNLOCKED;

    // 0 - unlocked
    // 1 - locked

    public static AccessStatus getAccessStatus(Enrollee somebody) {
        boolean accessStatus = somebody.isAccessAllowed();
        int access = 0;

        if(accessStatus){
            access=1;
        }
        System.out.println(access);
        return AccessStatus.values()[access];
    }

    public String getName() {
        return name().toLowerCase();
    }
}
