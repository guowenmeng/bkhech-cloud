# Getting Started

### Reference Documentation
> [JWT](https://jwt.io/)

### Guides

### JWT
> https://blog.csdn.net/weixin_42873937/article/details/82460997?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-2.control&dist_request_id=1328679.9305.16161366459285969&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-2.control
什么是JSON Web Token（JWT）
官方文档是这样解释的：JSON Web Token（JWT）是一个开放标准（RFC 7519），它定义了一种紧凑且独立的方式，可以在各方之间作为JSON对象安全地传输信息。此信息可以通过数字签名进行验证和信任。JWT可以使用秘密（使用HMAC算法）或使用RSA或ECDSA的公钥/私钥对进行签名。
虽然JWT可以加密以在各方之间提供保密，但只将专注于签名令牌。签名令牌可以验证其中包含的声明的完整性，而加密令牌则隐藏其他方的声明。当使用公钥/私钥对签署令牌时，签名还证明只有持有私钥的一方是签署私钥的一方。
通俗来讲，JWT是一个含签名并携带用户相关信息的加密串，页面请求校验登录接口时，请求头中携带JWT串到后端服务，后端通过签名加密串匹配校验，保证信息未被篡改。校验通过则认为是可靠的请求，将正常返回数据。

使用场景？
授权：这是最常见的使用场景，解决单点登录问题。因为JWT使用起来轻便，开销小，服务端不用记录用户状态信息（无状态），所以使用比较广泛；
信息交换：JWT是在各个服务之间安全传输信息的好方法。因为JWT可以签名，例如，使用公钥/私钥对儿 - 可以确定请求方是合法的。此外，由于使用标头和有效负载计算签名，还可以验证内容是否未被篡改。
JWT的结构体是什么样的？
JWT由三部分组成，分别是头信息、有效载荷、签名，中间以（.）分隔，如下格式：

xxx.yyy.zzz

header（头信息）
由两部分组成，令牌类型（即：JWT）、散列算法（HMAC、RSASSA、RSASSA-PSS等），例如：
```json
{
  "alg": "HS256",
  "typ": "JWT"
}
```
然后，这个JSON被编码为Base64Url，形成JWT的第一部分。

Payload（有效载荷）
JWT的第二部分是payload，其中包含claims。claims是关于实体（常用的是用户信息）和其他数据的声明，claims有三种类型： registered, public, and private claims。
Registered claims： 这些是一组预定义的claims，非强制性的，但是推荐使用， iss（发行人）， exp（到期时间）， sub（主题）， aud（观众）等；
Public claims: 自定义claims，注意不要和JWT注册表中属性冲突，这里可以查看JWT注册表
Private claims: 这些是自定义的claims，用于在同意使用这些claims的各方之间共享信息，它们既不是Registered claims，也不是Public claims。
以下是payload示例：
```json
{
  "sub": "1234567890",
  "name": "John Doe",
  "admin": true
}
```
然后，再经过Base64Url编码，形成JWT的第二部分；
注意：对于签名令牌，此信息虽然可以防止篡改，但任何人都可以读取。除非加密，否则不要将敏感信息放入到Payload或Header元素中。

Signature
要创建签名部分，必须采用编码的Header，编码的Payload，秘钥，Header中指定的算法，并对其进行签名。
例如，如果要使用HMAC SHA256算法，将按以下方式创建签名：
```java
HMACSHA256(
  base64UrlEncode(header) + "." +
  base64UrlEncode(payload),
  secret)
```
签名用于验证消息在此过程中未被篡改，并且，在使用私钥签名令牌的情况下，它还可以验证JWT的请求方是否是它所声明的请求方。
输出是三个由点分隔的Base64-URL字符串，可以在HTML和HTTP环境中轻松传递，与SAML等基于XML的标准相比更加紧凑。
例如：
```text
eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.
eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.
SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c
```
JWT工作机制？
在身份验证中，当用户使用其凭据成功登录时，将返回JSON Web Token（即：JWT）。由于令牌是凭证，因此必须非常小心以防止出现安全问题。一般情况下，不应将令牌保留的时间超过要求。理论上超时时间越短越好。

每当用户想要访问受保护的路由或资源时，用户代理应该使用Bearer模式发送JWT，通常在Authorization header中。标题内容应如下所示：
```properties
Authorization: Bearer <token>
```
在某些情况下，这可以作为无状态授权机制。服务器的受保护路由将检查Authorization header中的有效JWT ，如果有效，则允许用户访问受保护资源。如果JWT包含必要的数据，则可以减少查询数据库或缓存信息。
如果在Authorization header中发送令牌，则跨域资源共享（CORS）将不会成为问题，因为它不使用cookie。

注意：使用签名令牌，虽然他们无法更改，但是令牌中包含的所有信息都会向用户或其他方公开。这意味着不应该在令牌中放置敏感信息。


### Q&A
