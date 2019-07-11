<?php
$path0 = "tempfile/test.jpg";
$isMatched = preg_match('#/(?<=tempfile/).+#', $path0, $matches);
var_dump($isMatched, $matches);