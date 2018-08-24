
package net.homecredit.homerselect.ws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TransactionTypeDto.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TransactionTypeDto">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ATM"/>
 *     &lt;enumeration value="CSD"/>
 *     &lt;enumeration value="RTL"/>
 *     &lt;enumeration value="RET"/>
 *     &lt;enumeration value="DEP"/>
 *     &lt;enumeration value="CAD"/>
 *     &lt;enumeration value="INQ"/>
 *     &lt;enumeration value="CAV"/>
 *     &lt;enumeration value="OTH"/>
 *     &lt;enumeration value="INS"/>
 *     &lt;enumeration value="FEE"/>
 *     &lt;enumeration value="ICD"/>
 *     &lt;enumeration value="IPD"/>
 *     &lt;enumeration value="IIS"/>
 *     &lt;enumeration value="PMT"/>
 *     &lt;enumeration value="GAM"/>
 *     &lt;enumeration value="PRE"/>
 *     &lt;enumeration value="MNS"/>
 *     &lt;enumeration value="CDCD"/>
 *     &lt;enumeration value="CWCD"/>
 *     &lt;enumeration value="CDSS"/>
 *     &lt;enumeration value="BTIN"/>
 *     &lt;enumeration value="BTOUT"/>
 *     &lt;enumeration value="INT_BT_OUT_CL"/>
 *     &lt;enumeration value="EXT_BT_OUT_CL"/>
 *     &lt;enumeration value="PFC_POST"/>
 *     &lt;enumeration value="PFC_BANK"/>
 *     &lt;enumeration value="PFC_CARD"/>
 *     &lt;enumeration value="BI"/>
 *     &lt;enumeration value="SU"/>
 *     &lt;enumeration value="DAD"/>
 *     &lt;enumeration value="INS_CLAIM"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TransactionTypeDto", namespace = "http://homecredit.net/homerselect/ws/common/v2")
@XmlEnum
public enum TransactionTypeDto {


    /**
     * Homer transaction type: ATM Automated Teller Machine withdrawal
     * 
     */
    ATM,

    /**
     * Homer transaction type: CSD Cash Advance
     * 
     */
    CSD,

    /**
     * Homer transaction type: RTL Normal Purchase
     * 
     */
    RTL,

    /**
     * Homer transaction type: RET Merchandise Return
     * 
     */
    RET,

    /**
     * Homer transaction type: ATM DEP Deposit
     * 
     */
    DEP,

    /**
     * Deprecated: use DEP instead. Homer transaction type: CAD ATM Cash deposit.
     *                         - Used only in RU to enable deposit money with credit card
     * 
     */
    CAD,

    /**
     * Homer transaction type: INQ Balance Inquiry
     * 
     */
    INQ,

    /**
     * Homer transaction type: CAV Card verification.
     * 
     */
    CAV,

    /**
     * Homer transaction type: OTH Other
     * 
     */
    OTH,

    /**
     * Additional HS Transaction type: INS Insurance calculated by an external insurance module.
     * 
     */
    INS,

    /**
     * Additional HS Transaction type: FEE calculated by an external system.
     * 
     */
    FEE,

    /**
     * Additional HS Transaction type: Initial Cash Debit Tx.
     * 
     */
    ICD,

    /**
     * Additional HS Transaction type: Initial Pos Debit Tx.
     * 
     */
    IPD,

    /**
     * Additional HS Transaction type: Initial insurance
     * 
     */
    IIS,

    /**
     * Additional HS Transaction type: Payment.
     * 
     */
    PMT,

    /**
     * Homer transaction type: GAM - Gambling
     * 
     */
    GAM,

    /**
     * Homer transaction type: PRE Preauthorization
     * 
     */
    PRE,

    /**
     * Homer transaction type: MNS Money send. Credit side.
     * 
     */
    MNS,

    /**
     * Cash deposit at cash desk. Transaction without card.
     * 
     */
    CDCD,

    /**
     * Withdrawal cash at cash desk. Transaction without card.
     * 
     */
    CWCD,

    /**
     * Cash deposit through self-service terminal (kiosk). Transaction without card.
     * 
     */
    CDSS,

    /**
     * Acceptation of bank transfer (incoming). Transaction without card.
     * 
     */
    BTIN,

    /**
     * Execution of internal bank transfer (outgoing). Transaction without card.
     * 
     */
    BTOUT,

    /**
     * Execution of internal bank transfer (outgoing) for account termination (closing). Transaction without card.
     * 
     */
    INT_BT_OUT_CL,

    /**
     * Execution of external bank transfer (outgoing) for account termination (closing). Transaction without card.
     * 
     */
    EXT_BT_OUT_CL,

    /**
     * Incoming payment from post office. Transaction without card.
     * 
     */
    PFC_POST,

    /**
     * Incoming payment from foreign bank. Transaction without card.
     * 
     */
    PFC_BANK,

    /**
     * Funds received from foreign card, i.e. NurBank
     * 
     */
    PFC_CARD,

    /**
     * Bank income in case of account termination - Debit. Transfer of own funds to bank account.
     * 
     */
    BI,

    /**
     * (Close end account only - Deprecated: use DAD) Bank income in case of account termination needs incoming payment to pay debt(small underpayment) - Credit. Transfer from bank
     *                         account.
     * 
     */
    SU,

    /**
     * Bank income in case of account termination needs incoming payment to pay debt(like small underpayment) Deficits and Damages - Credit. Transfer from bank account.
     * 
     */
    DAD,

    /**
     * Insurance claim payment.
     * 
     */
    INS_CLAIM;

    public String value() {
        return name();
    }

    public static TransactionTypeDto fromValue(String v) {
        return valueOf(v);
    }

}
