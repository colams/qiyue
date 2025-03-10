package com.foxconn.sw.data.dto.enums.document;

import com.foxconn.sw.data.exception.BizException;

import java.util.ArrayList;
import java.util.List;

import static com.foxconn.sw.data.constants.enums.retcode.RetCode.ENUM_CONVERT_ERROR;
import static com.foxconn.sw.data.dto.enums.document.MainTypeEnums.*;

public enum SubTypeEnums implements IUniverseCode  {

    Team_Roster("Team Roster", Project),
    DFM("DFM", Project),
    Model_3D("3D-Model", Project),
    VSR("VSR", Project),
    ERS("ERS", Project),
    MCO("MCO", Project),
    Document_Tracker("Document Tracker", Project),
    REL("REL", Project),
    TEST("TEST", Project),
    MODULE("MODULE", Project),
    Drawing("Drawing", Project),
    Design_check_list("Design check list", Project),
    Design_Report("Design Report", Project),
    Allocation("Allocation", Project),
    Build_Matrix("Build Matrix", Project),
    Build_Plan("Build Plan", Project),
    DRP("DRP", Project),
    Meeting("會議類", Project),
    CTB("CTB", Project),
    IQC_FA_feedback("IQC FA feedback", Project),
    Material_Recognition("材料承認", Project),
    OCAP("OCAP", Project),
    QML("QML", Project),
    Machine_Tooling("Machine/ Tooling", Project),
    EQR("EQR", Project),
    UPH("UPH", Project),
    Efficiency("Efficiency", Project),
    MBO_PBO("MBO_PBO", Project),
    Cleanliness_Report_ESD("Cleanliness Report & ESD", Project),
    IQC_Summary_Report("IQC Summary Report", Project),
    FMEA("FMEA", Project),
    Process_Control_Plan("Process Control Plan", Project),
    POR_List("POR List", Project),
    QCP("QCP", Project),
    Reliability_Report("Reliability Report", Project),
    Test_Readiness("Test Readiness", Project),
    Others("Others", Project),
    Yield_Bridge("Yield Bridge", Project),
    Sifter("Sifter", Project),
    Waiver("Waiver", Project),
    COC("COC", Project),
    LimitS("LimitS", Project),
    NVM_Map_Verification("NVM Map Verification", Project),
    Test("Test", Project),
    Layout("Layout", Project),
    IOS("IOS", Project),
    D8("8D", Project),
    Technical_Report("Technical Report", Project),
    RTV_Tracker("RTV Tracker", Project),
    TA("TA", Project),
    BOM("BOM", Project),
    Process_Flow_CPF("Process Flow ( CPF )", Project),
    Budget("成本預算", Project),
    Request_Funds("請款", Project),
    Invoice("Invoice", Project),
    NPI_inventory_clearance("NPI庫存清理", Project),
    MIL("MIL", Project),
    Contact_Form("聯絡單", Project),
    WSG("WSG", Project),
    WI_Work_Instruction("WI (Work Instruction)", Project),
    OE("OE", Project),
    Defect_Code("Defect Code", Project),
    SOP("SOP", Project),


    Laws_and_Regulations("法律法規", HR),
    Customer_Specifications("客戶規范", HR),
    NPI_Conduct("NPI 行為準則", HR),
    Meeting_minutes("會議紀要", HR),
    Management_System_Review_Internal_Audit("管理體系評審-內審", HR),
    Management_System_Review_External_Audit("管理體系評審-外審", HR),
    RD_cycle("研發循環", HR),
    Notice("通知公告", HR),
    Fixed_Assets("固定資產管理", HR),
    Business_trips("出差類", HR),
    Organizational_structure("組織架構", HR),
    ET("教育訓練", HR),
    Work_saturation("工作飽和度", HR),
    Job_Responsibilities("工作職掌", HR),
    Other("其他類", HR),

    Company_Promotion("公司宣傳", BusinessAndMarket),
    Market_Research_Report("市場研究報告", BusinessAndMarket),
    Competitor_analysis("競爭對手分析", BusinessAndMarket),
    Customer_feedback("客戶反饋", BusinessAndMarket),
    ROI("ROI", BusinessAndMarket),

    Patents_And_Trade_Secrets("專利與營業秘密類", Intellectual_Property),
    FACA("FACA", Intellectual_Property),
    Lesson_learned("Lesson learned", Intellectual_Property),

    Supplier_related("供應商相關", Supply_Chain),
    Customs_logistics_related("關務物流相關", Supply_Chain),

    Default_File_Template("/", File_Template),
    Default_Workflow("/", Workflow),


    ;


    SubTypeEnums(String name, MainTypeEnums mainTypeEnums) {
        this.name = name;
        this.mainTypeEnums = mainTypeEnums;
    }

    private String name;
    private MainTypeEnums mainTypeEnums;

    public String getCode() {
        return name();
    }

    @Override
    public String getName() {
        return name;
    }

    public MainTypeEnums getMainTypeEnums() {
        return mainTypeEnums;
    }


    public static SubTypeEnums getEnumByCode(String code) {
        for (SubTypeEnums enums : SubTypeEnums.values()) {
            if (enums.name().equalsIgnoreCase(code)) {
                return enums;
            }
        }
        throw new BizException(ENUM_CONVERT_ERROR);
    }

    public static List<SubTypeEnums> getEnumsMainType(MainTypeEnums mainTypeEnums) {
        List<SubTypeEnums> subTypeEnumsList = new ArrayList<>();
        for (SubTypeEnums enums : SubTypeEnums.values()) {
            if (enums.equals(mainTypeEnums)) {
                subTypeEnumsList.add(enums);
            }
        }
        return subTypeEnumsList;
    }

    public static String getEnumNameByCode(String code) {
        for (SubTypeEnums enums : SubTypeEnums.values()) {
            if (enums.getCode().equalsIgnoreCase(code)) {
                return enums.getName();
            }
        }
        return code;
    }
}
