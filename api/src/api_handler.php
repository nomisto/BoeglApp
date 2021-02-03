<?php

if ((!defined('CONST_INCLUDE_KEY')) || (CONST_INCLUDE_KEY !== 'd4e2ad09-b1c3-4d70-9a9a-0e6149302486')) {
	// If someone tries to browse directly to this PHP file, send 404 and exit. It can only included
	// as part of our API.
	header("Location: /404.html", TRUE, 404);
	echo file_get_contents($_SERVER['DOCUMENT_ROOT'] . '/404.html');
	die;
}

class API_Handler {

	private $function_map;

	//--------------------------------------------------------------------------------------------------------------------
	public function __construct() {
		$this->loadFunctionMap();
	}

	//----------------------------------------------------------------------------------------------------------------------
	public function execCommand($varFunctionName, $varFunctionParams) {

		// get the actual function name (if necessary) and the class it belongs to.
		$returnArray = $this->getCommand($varFunctionName);

		// if we don't get a function back, then raise the error
		if ($returnArray['success'] == FALSE) {
			return $returnArray;
		}

		$class = $returnArray['dataArray']['class'];
		$functionName = $returnArray['dataArray']['function_name'];

		// Execute User Profile Commands
		$cObjectClass = new $class();
		$returnArray = $cObjectClass->$functionName($varFunctionParams);

		return $returnArray;

	}

	//----------------------------------------------------------------------------------------------------------------------
	private function getCommand($varFunctionName) {

		// get the actual function name and the class it belongs to.
		if (isset($this->function_map[$varFunctionName])) {
			$dataArray['class'] = $this->function_map[$varFunctionName]['class'];
			$dataArray['function_name'] = $this->function_map[$varFunctionName]['function_name'];
			$returnArray = App_Response::getResponse('200');
			$returnArray['dataArray'] = $dataArray;
		} else {
			$returnArray = App_Response::getResponse('405');
		}

		return $returnArray;

	}

	//----------------------------------------------------------------------------------------------------
	private function getAllTools($varParams) {
		
		// get the api key object
		$cApp_API_Key = new App_API_Key;
		$res = $cApp_API_Key->getAllTools();

		// if anything looks sketchy, bail.
		if ($res['response'] !== '200') {
			return $res;
		}

		return $res;
	}
	
	private function insertTool($varParams) {
		echo "HI";
		echo $varParams;
		$name = $varParams["name"];
		echo $name;
		// get the api key object
		$cApp_API_Key = new App_API_Key;
		$res = $cApp_API_Key->insertTool($name);

		// if anything looks sketchy, bail.
		if ($res['response'] !== '200') {
			return $res;
		}

		return $res;
	}
	
	private function deleteTool($varParams) {
		
		$id = $varParams["id"];
		
		// get the api key object
		$cApp_API_Key = new App_API_Key;
		$res = $cApp_API_Key->deleteTool($id);

		// if anything looks sketchy, bail.
		if ($res['response'] !== '200') {
			return $res;
		}

		return $res;
	}
	
	private function updateTool($varParams) {
		
		$id = $varParams["id"];
		$name = $varParams["name"];
		
		// get the api key object
		$cApp_API_Key = new App_API_Key;
		$res = $cApp_API_Key->updateTool($id, $name);

		// if anything looks sketchy, bail.
		if ($res['response'] !== '200') {
			return $res;
		}

		return $res;
	}
	
	private function getAllSites($varParams) {
		
		// get the api key object
		$cApp_API_Key = new App_API_Key;
		$res = $cApp_API_Key->getAllSites();

		// if anything looks sketchy, bail.
		if ($res['response'] !== '200') {
			return $res;
		}

		return $res;
	}
	
	private function insertSite($varParams) {
		echo "HI";
		echo $varParams;
		$name = $varParams["name"];
		echo $name;
		// get the api key object
		$cApp_API_Key = new App_API_Key;
		$res = $cApp_API_Key->insertSite($name);

		// if anything looks sketchy, bail.
		if ($res['response'] !== '200') {
			return $res;
		}

		return $res;
	}
	
	private function deleteSite($varParams) {
		
		$id = $varParams["id"];
		
		// get the api key object
		$cApp_API_Key = new App_API_Key;
		$res = $cApp_API_Key->deleteSite($id);

		// if anything looks sketchy, bail.
		if ($res['response'] !== '200') {
			return $res;
		}

		return $res;
	}
	
	private function updateSite($varParams) {
		$id = $varParams["id"];
		$name = $varParams["name"];
		$cApp_API_Key = new App_API_Key;
		$res = $cApp_API_Key->updateSite($id, $name);
		if ($res['response'] !== '200') {
			return $res;
		}
		return $res;
	}
	
	private function getHistoriesByToolId($varParams) {
		$toolid = $varParams["toolid"];
		$cApp_API_Key = new App_API_Key;
		$res = $cApp_API_Key->getHistoriesByToolId($toolid);
		if ($res['response'] !== '200') {
			return $res;
		}
		return $res;
	}
	
	private function getHistoriesBySiteId($varParams) {
		$toolid = $varParams["siteid"];
		$cApp_API_Key = new App_API_Key;
		$res = $cApp_API_Key->getHistoriesBySiteId($toolid);
		if ($res['response'] !== '200') {
			return $res;
		}
		return $res;
	}
	
	private function insertHistory($varParams) {
		$employee = $varParams["employee"];
		$from_date = $varParams["from_date"];
		$to_date = $varParams["to_date"];
		$toolid = $varParams["toolid"];
		$siteid = $varParams["siteid"];
		
		$cApp_API_Key = new App_API_Key;
		$res = $cApp_API_Key->insertHistory($employee, $from_date, $to_date, $toolid, $siteid);
		if ($res['response'] !== '200') {
			return $res;
		}
		return $res;
	}
	
	private function deleteHistory($varParams) {
		$id = $varParams["id"];
		
		$cApp_API_Key = new App_API_Key;
		$res = $cApp_API_Key->deleteHistory($id);
		if ($res['response'] !== '200') {
			return $res;
		}

		return $res;
	}
	
	private function updateHistory($varParams) {
		$id = $varParams["id"];
		$employee = $varParams["employee"];
		$from_date = $varParams["from_date"];
		$to_date = $varParams["to_date"];
		$toolid = $varParams["toolid"];
		$siteid = $varParams["siteid"];
		
		$cApp_API_Key = new App_API_Key;
		$res = $cApp_API_Key->updateHistory($id, $employee, $from_date, $to_date, $toolid, $siteid);
		if ($res['response'] !== '200') {
			return $res;
		}
		return $res;
	}

	//----------------------------------------------------------------------------------------------------------------------
	private function loadFunctionMap() {

		// load up all public facing functions
		$this->function_map = [
			'getAllTools' => ['class' => 'API_Handler', 'function_name' => 'getAllTools'],
			'insertTool' => ['class' => 'API_Handler', 'function_name' => 'insertTool'],
			'deleteTool' => ['class' => 'API_Handler', 'function_name' => 'deleteTool'],
			'updateTool' => ['class' => 'API_Handler', 'function_name' => 'updateTool'],
			'getAllSites' => ['class' => 'API_Handler', 'function_name' => 'getAllSites'],
			'insertSite' => ['class' => 'API_Handler', 'function_name' => 'insertSite'],
			'deleteSite' => ['class' => 'API_Handler', 'function_name' => 'deleteSite'],
			'updateSite' => ['class' => 'API_Handler', 'function_name' => 'updateSite'],
			'getHistoriesByToolId' => ['class' => 'API_Handler', 'function_name' => 'getHistoriesByToolId'],
			'getHistoriesBySiteId' => ['class' => 'API_Handler', 'function_name' => 'getHistoriesBySiteId'],
			'insertHistory' => ['class' => 'API_Handler', 'function_name' => 'insertHistory'],
			'updateHistory' => ['class' => 'API_Handler', 'function_name' => 'updateHistory'],
			'deleteHistory' => ['class' => 'API_Handler', 'function_name' => 'deleteHistory'],
		];

	}

} // end of class