<?php
if (!defined('ABSPATH')) {
    exit; // Exit if accessed directly.
}

require_once(__PDIR__ . '/common/_const.php');

function wpep_register_category( $categories ) {
    return array_merge(
        $categories,
        [
            [
                'slug'  => 'wp-spring',
                'title' => 'JAVA Spring Examples'
            ]
        ]
    );
}
add_action( 'block_categories', 'wpep_register_category', 10, 2 );

function wpep_init_block() {

	register_block_type(
		__DIR__,
		array(
			'render_callback' => 'wpep_render_block_callback',
		)
	);
}
add_action( 'init', 'wpep_init_block' );

function wpep_render_block_callback( $attributes, $content, $block_instance ) {
	ob_start();
	/**
	 * Keeping the markup to be returned in a separate file is sometimes better, especially if there is very complicated markup.
	 * All of passed parameters are still accessible in the file.
	 */
	require plugin_dir_path( __FILE__ ) . 'template.php';
	return ob_get_clean();
}
