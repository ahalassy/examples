<?php

/** 
 * Admin hooks
 */

if (!defined('ABSPATH')) {
	exit; // Exit if accessed directly.
}

add_action('admin_menu', 'cp_example_admin_init');
function cp_example_admin_init()
{
	require_once(__DIR__ . '/AdminController.php');

	$controller = new AdminController();
	$controller->add_submenu();
}

add_action('admin_action_test_config', 'wpep_admin_action_test');
function wpep_admin_action_test()
{
	$controller = new AdminController();
	$controller->test_configuration();
	$url = $_SERVER['HTTP_REFERER'];
	wp_redirect($url);
	exit();
}

add_action('admin_action_save_config', 'wpep_admin_action_save');
function wpep_admin_action_save()
{
	$controller = new AdminController();
	$controller->save_configuration();
	$url = $_SERVER['HTTP_REFERER'];
	wp_redirect($url);
	exit();
}

add_action('admin_notices', 'wpep_show_admin_notices');
function wpep_show_admin_notices()
{
	function show_notice($option_key, $type) {
		$notice = get_option($option_key, false);
		if ($notice) {
			delete_option($option_key);
			$class = '"notice notice-' . $type . ' is-dismissible"';
			echo <<<HTML
			<div class=$class>
				<p>$notice</p>
			</div>
		HTML;
		}

	}

	show_notice(WPEP_OPTION_SUCCESS, 'success');
	show_notice(WPEP_OPTION_FAIL, 'error');
}
