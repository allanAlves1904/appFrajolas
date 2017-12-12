<?php
    
    $con = mysqli_connect("localhost", "root", "bcd127", "dbfrajolas");

    $sql = "select * from tbl_produtos;";

    $resultado = mysqli_query($con, $sql);

    $lstProdutos = array();

    while( $produto = mysqli_fetch_assoc($resultado)){
        
        $lstProdutos [] =  $produto;           
        
        
    }

    echo json_encode($lstProdutos);

?>