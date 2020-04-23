package ua.nure.popova.SummaryTask4.db;

import ua.nure.popova.SummaryTask4.db.entity.Enrollee;

public enum EntranceStatus {
     DIDNT_ENTER, ENTERED;

    // 0 - didn't enter
    // 1 - entered

    public static EntranceStatus getEntranceStatus(Enrollee somebody) {
        int entranceStatus = somebody.getEntranceStatus();
        System.out.println(entranceStatus);
        return EntranceStatus.values()[entranceStatus];
    }

    public String getName() {
        return name().toLowerCase();
    }

}
