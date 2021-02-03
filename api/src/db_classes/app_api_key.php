<?php 
/*
 * This file is part of the "Another" suite of products.
 *
 * (c) 2020 Another, LLC
 *
 */

if ((!defined('CONST_INCLUDE_KEY')) || (CONST_INCLUDE_KEY !== 'd4e2ad09-b1c3-4d70-9a9a-0e6149302486')) {
	// if accessing this class directly through URL, send 404 and exit
	// this section of code will only work if you have a 404.html file in your root document folder.
	header("Location: /404.html", TRUE, 404);
	echo file_get_contents($_SERVER['DOCUMENT_ROOT'] . '/404.html');
	die;
}

//----------------------------------------------------------------------------------------------------------------------
class App_API_Key extends Data_Access {

	protected $object_name = 'app_api_key';
	protected $object_view_name = 'vw_app_api_key';

	//----------------------------------------------------------------------------------------------------
	public function __construct() {
        // attempt database connection
        $res = $this->dbConnect();
        
        // if we get anything but a good response ...
        if ($res['response'] != '200') {
            echo "Houston? We have a problem.";
            die;
        }
	}

	//----------------------------------------------------------------------------------------------------
	public function getAllTools() {
		$query = "SELECT * FROM tools";
		$res = $this->getResultSetArray($query);
		return $res;
	}
	
	public function insertTool($name) {
		$query = "INSERT INTO Tools (NAME) VALUES ('" . $name . "')";
		$res = $this->getResultSetArray($query);
		return $res;
	}
	
	public function deleteTool($id) {
		$query = "DELETE FROM Tools WHERE ID =" . $id;
		$res = $this->getResultSetArray($query);
		return $res;
	}
	
	public function updateTool($id, $name) {
		$query = "UPDATE Tools SET NAME = '" . $name . "' WHERE id = " . $id;
		$res = $this->getResultSetArray($query);
		return $res;
	}
	
	public function getAllSites() {
		$query = "SELECT * FROM sites";
		$res = $this->getResultSetArray($query);
		return $res;
	}
	
	public function insertSite($name) {
		$query = "INSERT INTO Sites (NAME) VALUES ('" . $name . "')";
		$res = $this->getResultSetArray($query);
		return $res;
	}
	
	public function deleteSite($id) {
		$query = "DELETE FROM sites WHERE ID =" . $id;
		$res = $this->getResultSetArray($query);
		return $res;
	}
	
	public function updateSite($id, $name) {
		$query = "UPDATE sites SET NAME = '" . $name . "' WHERE id = " . $id;
		$res = $this->getResultSetArray($query);
		return $res;
	}
	
	public function getHistoriesByToolId($toolid) {
		$query = "SELECT * from History WHERE TOOL_ID = " . $toolid;
		$res = $this->getResultSetArray($query);
		return $res;
	}
	
	public function getHistoriesBySiteId($siteid) {
		$query = "SELECT * from History WHERE SITE_ID = " . $siteid;
		$res = $this->getResultSetArray($query);
		return $res;
	}
	
	public function insertHistory($employee, $from_date, $to_date, $toolid, $siteid){
		$query = "INSERT INTO History (TOOL_ID, SITE_ID, EMPLOYEE, FROM_DATE, TO_DATE) VALUES (" . $toolid . "," . $siteid . ",'" . $employee . "'," . $from_date . "," . $to_date . ")";
		$res = $this->getResultSetArray($query);
		return $res;
	}
	
	public function deleteHistory($id){
		$query = "DELETE FROM history WHERE ID =" . $id;
		$res = $this->getResultSetArray($query);
		return $res;
	}
	
	public function updateHistory($id, $employee, $from_date, $to_date, $toolid, $siteid){
		$query = "UPDATE sites SET EMPLOYEE = '" . $employee . "', TOOL_ID = " . $toolid . ", SITE_ID = " . $siteid . ", FROM_DATE = " . $from_date . ", TO_DATE = " . $to_date . " WHERE id = " . $id;
		$res = $this->getResultSetArray($query);
		return $res;
	}

} // end class