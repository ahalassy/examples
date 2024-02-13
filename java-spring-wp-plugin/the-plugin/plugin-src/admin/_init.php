<?php
/** 
 * Admin hooks
 */

 if ( ! defined( 'ABSPATH' ) ) {
	exit; // Exit if accessed directly.
}

function cp_example_admin_init() {
    require_once(__DIR__.'/AdminController.php');

	$settings = new AdminController();
	$settings->add_submenu();
}

add_action('admin_menu', 'cp_example_admin_init');