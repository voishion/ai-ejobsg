https://doc.muluhub.com/docs/chatgpt/chatgpt-1f1usj6gl3bit



proxy_buffering off;

upstream chatgpt-web {
    server 127.0.0.1:1002 weight=1;
}

server {
  listen 80;
  server_name www.替换的域名 替换的域名;
  location / {
    rewrite ^(.*)$ https://www.替换的域名;
  }
}

server {
  listen 443 ssl;
  server_name www.替换的域名;
  ssl_certificate /etc/nginx/替换的SSL证书.pem;
  ssl_certificate_key /etc/nginx/替换的SSL证书秘钥.key;
  location / {
    proxy_pass http://chatgpt-web;
  }
}
