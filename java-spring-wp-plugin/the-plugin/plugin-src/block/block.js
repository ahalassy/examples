
( function ( blocks, i18n, element, blockEditor ) {
	var el = element.createElement;
	var __ = i18n.__;
	var useBlockProps = blockEditor.useBlockProps;

	var blockStyle = {
		backgroundColor: '#900',
		color: '#fff',
		padding: '20px',
	};

	blocks.registerBlockType( 'wp-spring/example-block', {
		edit: function () {
			return el(
				'p',
				useBlockProps( { style: blockStyle } ),
				__(
					'Editor',
					'wp-spring'
				)
			);
		},
		save: function () {
			return el(
				'p',
				useBlockProps.save( { style: blockStyle } ),
				__(
					'Saved.',
					'wp-spring'
				)
			);
		},
	} );
} )(
	window.wp.blocks,
	window.wp.i18n,
	window.wp.element,
	window.wp.blockEditor
);