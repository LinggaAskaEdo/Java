<?php

/*
 * (c) 2014 1rstWAP. All rights reserved
 * 
 * System           : Altamides
 * Module           : Telcotrax
 * Version          : 2.0 SP19
 * Filename         : TacDataImporter.php
 * Fileversion      : 2.000.000
 * Initial Creation : 2014-01-18
 * Initial Author   : Pancasilany D. Pattipeiluhu
 * Purpose          : Library for import TAC data
 * ================================================
 * Initial Request  : #3750
 * ================================================
 * Change Log
 * Date         Author						Version     Request     Comment         
 * 2014-01-18   Pancasilany D. Pattipeiluhu	2.0SP19	    #3750		Initial creation
 * 
 * PT. 1rstWAP confidential
 * This document belongs to PT 1rstWAP. Propagation to others
 * then members of PT. 1rstWAP is strictly forbidden.
 */

namespace Firstwap\Altamides\Telcotrax\Imei;

use Firstwap\Altamides\Db\DbManager,
	Firstwap\Altamides\Db\DbNameRef,
	\PDO;

class TacDataImporter
{

	public function getMeTypeId($meType)
	{
		try {
			$dbManager	 = DbManager::getInstance();
			$dbName		 = DbNameRef::getName(DbNameRef::TELCO);
			$conn		 = $dbManager->getPdo(DbNameRef::TELCO);
			$meTypeId	 = 1;

			$query = "SELECT ME_TYPE_ID FROM $dbName.ME_TYPE WHERE ME_TYPE_DESC = :meType";

			$stmt = $conn->prepare($query);
			$stmt->bindParam(':meType', $meType, PDO::PARAM_STR);
			$stmt->execute();

			if ($stmt->rowCount()) {
				$result		 = $stmt->fetch(\PDO::FETCH_ASSOC);
				$meTypeId	 = $result['ME_TYPE_ID'];
			}

			return $meTypeId;
		} catch (\PDOException $e) {
			error_log("[getMeTypeId] Query error: $query");
			return false;
		} catch (Exception $e) {
			error_log('[getMeTypeId] ERROR: ' . $e->getMessage() . PHP_EOL);
			return false;
		}
	}

	public function checkExistingTacAutData($tacDataId)
	{
		try {
			$dbManager	 = DbManager::getInstance();
			$dbName		 = DbNameRef::getName(DbNameRef::TELCO);
			$conn		 = $dbManager->getPdo(DbNameRef::TELCO);
			$isInsert	 = false;

			$query = "SELECT TAC_DATA_ID FROM $dbName.TAC_AUTHENTIC_DATA WHERE TAC = :tacDataId";

			$stmt = $conn->prepare($query);
			$stmt->bindParam(':tacDataId', $tacDataId, PDO::PARAM_STR);
			$stmt->execute();
			if (!$stmt->rowCount()) {
				$isInsert = true;
			}

			return $isInsert;
		} catch (\PDOException $e) {
			error_log("[checkExistingTacAutData] Query error: $query");
			return false;
		} catch (Exception $e) {
			error_log('[checkExistingTacAutData] ERROR: ' . $e->getMessage() . PHP_EOL);
			return false;
		}
	}

	public function checkExistingTacData($tacDataId)
	{
		try {
			$dbManager	 = DbManager::getInstance();
			$dbName		 = DbNameRef::getName(DbNameRef::TELCO);
			$conn		 = $dbManager->getPdo(DbNameRef::TELCO);
			$isInsert	 = "";

			$query = "SELECT * FROM $dbName.TAC_DATA WHERE TAC = :tacDataId";

			$stmt = $conn->prepare($query);
			$stmt->bindParam(':tacDataId', $tacDataId, PDO::PARAM_STR);
			$stmt->execute();
			if ($stmt->rowCount()) {
				$result		 = $stmt->fetch(\PDO::FETCH_ASSOC);
				$isInsert	 = $result['IS_MODIFIED'];
			}

			return $isInsert;
		} catch (\PDOException $e) {
			error_log("[checkExistingTacData] Query error: $query");
			return false;
		} catch (Exception $e) {
			error_log('[checkExistingTacData] ERROR: ' . $e->getMessage() . PHP_EOL);
			return false;
		}
	}

	public function insertTac($paramsTacAut, $paramsUpdateTacAut, $paramsTac, $paramsUpdateTac,
						   $isRealTransaction)
	{
		try {
			$dbManager				 = DbManager::getInstance();
			$dbName					 = DbNameRef::getName(DbNameRef::TELCO);
			$conn					 = $dbManager->getPdo(DbNameRef::TELCO);
			$totalDataTacAut		 = count($paramsTacAut);
			$totalDataTac			 = count($paramsTac);
			$paramsTacAut			 = implode(",", $paramsTacAut);
			$paramsTac				 = implode(",", $paramsTac);
			$totalDataUpdateTacAut	 = 0;
			$updateTADTac			 = "";
			$totalDataUpdateTac		 = 0;
			$updateTAD				 = "";

			$queryInsertTacAut = "INSERT INTO $dbName.TAC_AUTHENTIC_DATA 
					  (TAC,MANUFACTURER_NAME,MODEL_NAME,ME_TYPE_ID,TAC_NOTES,MODIFIED_TIMESTAMP)
					 VALUES $paramsTacAut";

			$queryInsertTac = "INSERT INTO $dbName.TAC_DATA 
					  (TAC,MANUFACTURER_NAME,MODEL_NAME,ME_TYPE_ID,TAC_NOTES,MODIFIED_TIMESTAMP)
					 VALUES $paramsTac";
			
			if (count($paramsUpdateTacAut) > 0) {
				$totalDataUpdateTacAut	 = count($paramsUpdateTacAut["tac"]);
				$updateTADTac			 = implode(",", $paramsUpdateTacAut['tac']);
			
				$queryUpdateTacAut = "UPDATE $dbName.TAC_AUTHENTIC_DATA 
								SET 
								MANUFACTURER_NAME=" . $paramsUpdateTacAut['manufacture'] . ",
								MODEL_NAME=" . $paramsUpdateTacAut['model'] . ",
								ME_TYPE_ID=" . $paramsUpdateTacAut['type'] . ",
								TAC_NOTES=" . $paramsUpdateTacAut['notes'] . ",
								MODIFIED_TIMESTAMP=NOW()
								WHERE TAC IN(" . $updateTADTac . ")
							   ";
			}
			
			if (count($paramsUpdateTac) > 0) {
				$totalDataUpdateTac	 = count($paramsUpdateTac["tac"]);
				$updateTAD			 = implode(",", $paramsUpdateTac['tac']);

				$queryUpdateTac = "UPDATE $dbName.TAC_DATA 
							SET 
							MANUFACTURER_NAME=" . $paramsUpdateTac['manufacture'] . ",
							MODEL_NAME=" . $paramsUpdateTac['model'] . ",
							ME_TYPE_ID=" . $paramsUpdateTac['type'] . ",
							TAC_NOTES=" . $paramsUpdateTac['notes'] . ",
							MODIFIED_TIMESTAMP=NOW(),
							MODIFIED_BY=NULL
							WHERE TAC IN(" . $updateTAD . ") AND IS_MODIFIED=0
						   ";
			}

			$conn->beginTransaction();
			if ($totalDataTacAut > 0) {
				$stmtInsertTacAut = $conn->prepare($queryInsertTacAut);
				$stmtInsertTacAut->execute();
			}

			if ($totalDataUpdateTacAut > 0) {
				$stmtUpdateTacAut = $conn->prepare($queryUpdateTacAut);
				$stmtUpdateTacAut->execute();
			}
			if ($totalDataTac > 0) {
				$stmtInsertTac = $conn->prepare($queryInsertTac);
				$stmtInsertTac->execute();
			}
			if ($totalDataUpdateTac > 0) {
				$stmtUpdateTac = $conn->prepare($queryUpdateTac);
				$stmtUpdateTac->execute();
			}

			if ($isRealTransaction) {
				$conn->commit();
				if (!empty($totalDataTacAut)) {
					echo "Insert <$totalDataTacAut> data(s) into TAC_AUTHENTIC_DATA table \n";
				}
				if (!empty($totalDataUpdateTacAut)) {
					echo "Update <$totalDataUpdateTacAut> data(s) into TAC_AUTHENTIC_DATA table \n";
				}
				if (!empty($totalDataTac)) {
					echo "Insert <$totalDataTac> data(s) into TAC_DATA table \n";
				}
				if (!empty($totalDataUpdateTac)) {
					echo "Update <$totalDataUpdateTac> data(s) into TAC_DATA table \n";
				}
			} else {
				echo "This is DRY RUN: cancelling all changes...\n";
				$conn->rollBack();
			}
			return true;
		} catch (Exception $e) {
			if ($conn) {
				$conn->rollBack();
			}
			error_log('[insertTacData] ERROR: ' . $e->getMessage() . PHP_EOL);
			return false;
		}
	}

}

?>
