package com.wxw.cloud.controller;

import com.wxw.cloud.domain.Cart;
import com.wxw.cloud.service.CartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: wxw
 * @create: 2020-04-23-14:34
 */
@RestController
@Slf4j
@Api(tags = "CartController",description = "购物车微服务")
public class CartController {

    @Resource
    private CartService cartService;


    @ApiOperation("添加购物车")
    @PostMapping
    public ResponseEntity<Void> addCart(@RequestBody Cart cart){
           this.cartService.addCart(cart);
           return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 查询购物车
     */
    @ApiOperation("查询购物车")
    @GetMapping("/find")
    public ResponseEntity<List<Cart>> queryCarts(){
        log.info("Start查询购物车：{}");
        List<Cart> carts = this.cartService.queryCarts();
        if (CollectionUtils.isEmpty(carts)){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        log.info("End 查询购物车：{}",carts);
        return ResponseEntity.ok(carts);
    }

    @ApiOperation("修改购物车数量")
    @PutMapping
    public ResponseEntity<Void> updateNum(@RequestBody Cart cart){
        this.cartService.updateNum(cart);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation("删除购物车")
    @DeleteMapping("{skuId}")
    public ResponseEntity<Void> deleteCart(@PathVariable("skuId")String skuId){
          this.cartService.deleteCart(skuId);
          return ResponseEntity.ok().build();
    }




















}
