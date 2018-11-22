config 统一配置中心 配置从远程git拉取配置信息到本地git config服务端

拉取规则
/{name}-{profiles}.yml
/{label}/{name}-{profiles}.yml

会同时加载{name}.yml 和/{name}-{profiles}.yml

name:服务名
profiles:环境
label：分支(branch)

配置中心的高可用和普通服务高可用一样