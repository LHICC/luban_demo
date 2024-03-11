# readme

## 运行项目

> 😈注意：已经将三个服务打包并整理好，将代码拉取之后可以跳过打包环节

-   拉取仓库代码
    1.  idea拉取仓库代码后加载maven模块(不行就手动添加有pom文件的模块)

        ![](./doc/readme/image_rRC1WzKuRf.png)
    2.  将项目打包，将生成的`app.jar`放到`apps`文件中的对应服务的文件夹（可以跳过）

        需要先将基础模块执行`install`，然后执行其他模块,执行后生成app.jar包

        ①lb-parent

        ②lb-base

        ③lb-product/lb-product-entity

        ④lb-user/lb-user-entity

        ⑤lb-user/lb-user-api

        ⑥其他模块可以随意
-   运行（已经将三个服务打包并整理好可以直接运行）
    1.  找到doc目录中的apps文件（apps文件）

        apps文件目录详情

        ![](./doc/readme/image_-PW3ZYxw_B.png)
    2.  将`apps`文件上传服务器或虚拟机（必须根目录）
    3.  进入`apps`文件中，执行`docker-compose up -d`
        ```bash
        cd /apps
        
        docker-compose up -d
        ```
    4.  等待服务加载完成

        ![](./doc/readme/image_XyStooL0_R.png)

        详情：
        ```bash
        lb-ldap→ldap服务
        apps_userservice_1→用户服务
        go-ldap-admin-phpldapadmin→ldap图形化工具（删除）
        apps_productservice_1→产品服务
        apps_gateway_1→网关服务
        lb-mysql→mysql服务
        lb-nacos→nacos服务
        ```
        注意：因为nacos启动比较慢的原因，可能导致网关，用户，产品服务启动失败，需要重启服务（特别是网关）
        ```bash
        docker restart 容器CONTAINERID
        
        ```

## 测试（命令行）

> 😈注意：
>
> 1.  token：需要将获取到的tonken同步修改到下方的curl操作中
> 2.  访问慢：由于没有添加缓存，且权限校验有大量查询，所以在访问api时会很慢
> 3.  测试工具：
>
>     使用Apifox：邀请你加入团队 luban\_demo [https://app.apifox.com/invite?token=8giUcmKsXHsizN8GZp9tw](https://app.apifox.com/invite?token=8giUcmKsXHsizN8GZp9tw "https://app.apifox.com/invite?token=8giUcmKsXHsizN8GZp9tw")
>
>     注意：需要更改测试环境中的ip地址为你的服务器ip

-   获取token

    账号登录（type=account）
    ```bash
    curl --location --request GET 'http://localhost:7573/api/user/anno/token?account=editor_1&password=editor_1&type=account' \
    --header 'token: eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiI4NTY5NjEwMTk0NzIyNTgzMDUiLCJhY2NvdW50IjoiZWRpdG9yXzEiLCJuYW1lIjoiZWRpdG9yXzEiLCJvcmdpZCI6ODU2OTU5ODY4MzU4MTA2MjA5LCJzdGF0aW9uaWQiOjg1Njk2MDEyNDY1OTQ0MTA4OSwiZXhwIjoxNzEwMTY4OTMyfQ.iwF0dKx1YRLswBGuUY5JiY5RjSFql7tIYnUBCrqlLooNztDOK9-R0CKHyXlHCucd9r0Ta29VjXYqiqd3D8GKFWCATs8UNZh1RYUxTFH1aQ_jBa8jiDpcyk2K_exYxuvtsaMJ-jXAcsmqoZ4GzL72_HyFNaZEqDx522mG-uvVqlA' \
    --header 'User-Agent: Apifox/1.0.0 (https://apifox.com)' \
    --header 'Accept: */*' \
    --header 'Connection: keep-alive'
    ```
    ldap登录（type=LDAP）
    ```bash
    curl --location --request GET 'http://localhost:7573/api/user/anno/token?account=editor_1&password=editor_1&type=LDAP' \
    --header 'token: eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiI4NTY5NjEwMTk0NzIyNTgzMDUiLCJhY2NvdW50IjoiZWRpdG9yXzEiLCJuYW1lIjoiZWRpdG9yXzEiLCJvcmdpZCI6ODU2OTU5ODY4MzU4MTA2MjA5LCJzdGF0aW9uaWQiOjg1Njk2MDEyNDY1OTQ0MTA4OSwiZXhwIjoxNzEwMTY4OTMyfQ.iwF0dKx1YRLswBGuUY5JiY5RjSFql7tIYnUBCrqlLooNztDOK9-R0CKHyXlHCucd9r0Ta29VjXYqiqd3D8GKFWCATs8UNZh1RYUxTFH1aQ_jBa8jiDpcyk2K_exYxuvtsaMJ-jXAcsmqoZ4GzL72_HyFNaZEqDx522mG-uvVqlA' \
    --header 'User-Agent: Apifox/1.0.0 (https://apifox.com)' \
    --header 'Accept: */*' \
    --header 'Connection: keep-alive'
    ```
-   添加产品
    ```bash
    curl --location --request POST 'http://localhost:7573/api/product/add' \
    --header 'token: eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiI4NTY5NjEwMTk0NzIyNTgzMDUiLCJhY2NvdW50IjoiZWRpdG9yXzEiLCJuYW1lIjoiZWRpdG9yXzEiLCJvcmdpZCI6ODU2OTU5ODY4MzU4MTA2MjA5LCJzdGF0aW9uaWQiOjg1Njk2MDEyNDY1OTQ0MTA4OSwiZXhwIjoxNzEwMTY4OTMyfQ.iwF0dKx1YRLswBGuUY5JiY5RjSFql7tIYnUBCrqlLooNztDOK9-R0CKHyXlHCucd9r0Ta29VjXYqiqd3D8GKFWCATs8UNZh1RYUxTFH1aQ_jBa8jiDpcyk2K_exYxuvtsaMJ-jXAcsmqoZ4GzL72_HyFNaZEqDx522mG-uvVqlA' \
    --header 'User-Agent: Apifox/1.0.0 (https://apifox.com)' \
    --header 'Content-Type: application/json' \
    --header 'Accept: */*' \
    --header 'Connection: keep-alive' \
    --data '{
        "name": "及新个民参"
    }'
    
    ```
-   查询产品列表
    ```bash
    curl --location --request GET 'http://localhost:7573/api/product/list?keyWord&pageNum=1&pageSize=10' \
    --header 'token: eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiI4NTY5NjEwMTk0NzIyNTgzMDUiLCJhY2NvdW50IjoiZWRpdG9yXzEiLCJuYW1lIjoiZWRpdG9yXzEiLCJvcmdpZCI6ODU2OTU5ODY4MzU4MTA2MjA5LCJzdGF0aW9uaWQiOjg1Njk2MDEyNDY1OTQ0MTA4OSwiZXhwIjoxNzEwMTY4OTMyfQ.iwF0dKx1YRLswBGuUY5JiY5RjSFql7tIYnUBCrqlLooNztDOK9-R0CKHyXlHCucd9r0Ta29VjXYqiqd3D8GKFWCATs8UNZh1RYUxTFH1aQ_jBa8jiDpcyk2K_exYxuvtsaMJ-jXAcsmqoZ4GzL72_HyFNaZEqDx522mG-uvVqlA' \
    --header 'User-Agent: Apifox/1.0.0 (https://apifox.com)' \
    --header 'Accept: */*' \
    --header 'Connection: keep-alive'
    ```
-   更新产品（注意id必须是存在的）
    ```bash
    curl --location --request PUT 'http://localhost:7573/api/product/update' \
    --header 'token: eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiI4NTY5NjEwMTk0NzIyNTgzMDUiLCJhY2NvdW50IjoiZWRpdG9yXzEiLCJuYW1lIjoiZWRpdG9yXzEiLCJvcmdpZCI6ODU2OTU5ODY4MzU4MTA2MjA5LCJzdGF0aW9uaWQiOjg1Njk2MDEyNDY1OTQ0MTA4OSwiZXhwIjoxNzEwMTY4OTMyfQ.iwF0dKx1YRLswBGuUY5JiY5RjSFql7tIYnUBCrqlLooNztDOK9-R0CKHyXlHCucd9r0Ta29VjXYqiqd3D8GKFWCATs8UNZh1RYUxTFH1aQ_jBa8jiDpcyk2K_exYxuvtsaMJ-jXAcsmqoZ4GzL72_HyFNaZEqDx522mG-uvVqlA' \
    --header 'User-Agent: Apifox/1.0.0 (https://apifox.com)' \
    --header 'Content-Type: application/json' \
    --header 'Accept: */*' \
    --header 'Connection: keep-alive' \
    --data '{
        "id": 21,
        "name": "它多世"
    }'
    ```
-   删除产品（注意id必须是存在的）
    ```bash
    curl --location --request DELETE 'http://localhost:7573/api/product/delete/77' \
    --header 'token: eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiI4NTY5NjEwMTk0NzIyNTgzMDUiLCJhY2NvdW50IjoiZWRpdG9yXzEiLCJuYW1lIjoiZWRpdG9yXzEiLCJvcmdpZCI6ODU2OTU5ODY4MzU4MTA2MjA5LCJzdGF0aW9uaWQiOjg1Njk2MDEyNDY1OTQ0MTA4OSwiZXhwIjoxNzEwMTY4OTMyfQ.iwF0dKx1YRLswBGuUY5JiY5RjSFql7tIYnUBCrqlLooNztDOK9-R0CKHyXlHCucd9r0Ta29VjXYqiqd3D8GKFWCATs8UNZh1RYUxTFH1aQ_jBa8jiDpcyk2K_exYxuvtsaMJ-jXAcsmqoZ4GzL72_HyFNaZEqDx522mG-uvVqlA' \
    --header 'User-Agent: Apifox/1.0.0 (https://apifox.com)' \
    --header 'Accept: */*' \
    --header 'Connection: keep-alive'
    ```
