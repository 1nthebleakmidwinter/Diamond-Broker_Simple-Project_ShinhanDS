package com.personal;

enum Screen {   // state    parent
    INIT,       // 0        -
    SIGNIN,     // 1        0
    SIGNUP,     // 2        0
    EXIT,       // 3        0
    MAIN,       // 4        1
    SINGED_UP,  // 5        2 (abstract)
    STORE,      // 6        4
    SEARCH,     // 7        6
    DIA_TO_DIBS,// 8        6 (abstract)

    SEL_PRICE,  // 9        7
    SEL_CARAT,  // 10       7
    SEL_CUT,    // 11       7
    SEL_COLOR,  // 12       7
    SEL_CLAR,   // 13       7
    SEL_DEPTH,  // 14       7
    SEL_TABLE,  // 15       7
    EXEC_SCH,   // 16       6

    SET_FEAT,   // 17       7 (abstract)

    PROFILE,     // 18       4

    CHG_NAME,   // 19       18
    CHG_PN,     // 20       18
    CHG_EMAIL,  // 21       18
    CHG_CARD,   // 22       18
    CHARGE,     // 23       18
    INQUIRY,    // 24       18
    CHANGE,     // 25       18 (abstract)
    CANCEL,     // 26       18 (abstract)
    REG_DIA,    // 27       4
    REG_CUT,    // 28       27
    REG_COLOR,  // 29       27
    REG_CLARITY,// 30       27

    REG_FEAT,    // 31       27 (abstract)
    EVAL,        // 32          (abstract)
    REGISTER,    // 33          (abstract)

    DIB_TABLE,   // 34
    BUYORDROP,   // 35
    BUY,         // 36          (abstract)
    DROP         // 37          (abstract)
}