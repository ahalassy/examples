<?php
        require_once(__PDIR__.'/common/ExampleService.php');
        $service = new ExampleService();
        $me = $service->get_me();
?>
<h1>HELLO</h1>
<p><?php
    var_dump($me);
?>
</p>