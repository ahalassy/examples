<?php
if ( ! defined( 'ABSPATH' ) ) {
	exit; // Exit if accessed directly.
}

require_once(__PDIR__ . '/common/ExampleService.php');

class AdminController {

	private $service;

	function __construct() { 
		$this->service = new ExampleService();
	}

	function add_submenu() {
		if( !function_exists('add_submenu_page') ){
			require_once( ABSPATH . 'wp-admin/includes/plugin.php' );
		}

		add_submenu_page(
			'options-general.php',
			'Examples',
			'Example Spring Plugin',
			'manage_options',
			'example-settings',
			array($this, 'render_submenu'),
		);
	}

	function render_submenu() {
		include_once(__DIR__.'/render.php');
	}

	function save_configuration() {
		$url = $_POST['url'];
		$mail = $_POST['email'];
		$pass = $_POST['password'];
		$form = [
			'email' => $mail,
			'password' => $pass
		];

		$response = wp_remote_post( $url . '/api/user/key', array('body' => $form));
		$status_code = $response['response']['code'];
		$success = 200 <= $status_code && $status_code < 300;

		if ($success) {
			$this->service->save_config($url, $mail, $response['body']);
			$notice = 'Connection saved.';
    		update_option( 'wpep_connection_success', $notice, 'no' );

		} else {
			$notice = 'Could not connect to the server.';
    		update_option( 'wpep_connection_fail', $notice, 'no' );

		}

	}

	function test_configuration() {
		$url = $_POST['url'];
		$mail = $_POST['email'];
		$pass = $_POST['password'];
		$form = [
			'email' => $mail,
			'password' => $pass
		];

		$response = wp_remote_post( $url . '/api/auth', array('body' => $form));
		$status_code = $response['response']['code'];
		$success = 200 <= $status_code && $status_code < 300;

		if ($success) {
			$notice = 'Connection established.';
    		update_option( 'wpep_connection_success', $notice, 'no' );

		} else {
			$notice = 'Could not connect to the server.';
    		update_option( 'wpep_connection_fail', $notice, 'no' );

		}
	}

}