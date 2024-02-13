<?php
if ( ! defined( 'ABSPATH' ) ) {
	exit; // Exit if accessed directly.
}

class AdminController {
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

}