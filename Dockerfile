FROM ubuntu:24.04

ENV DEBIAN_FRONTEND=noninteractive

# 安装基础工具和依赖
RUN apt-get update && apt-get install -y \
    curl zip unzip git bash tar \
    build-essential zlib1g-dev libz-dev libstdc++6 \
    libjemalloc-dev \
    gdb gdbserver \
    libssl-dev \
    procps \
    ca-certificates \
    && apt-get clean && rm -rf /var/lib/apt/lists/*

# 设置 SDKMAN 环境变量
ENV SDKMAN_DIR="/root/.sdkman"
ENV JAVA_VERSION="21.0.7-graal"
ENV MAVEN_VERSION="3.9.6"
ENV PATH="${SDKMAN_DIR}/candidates/java/current/bin:${SDKMAN_DIR}/candidates/maven/current/bin:${PATH}"

# 安装 SDKMAN + GraalVM + Maven + native-image
RUN curl -s "https://get.sdkman.io" | bash && \
    bash -c "source ${SDKMAN_DIR}/bin/sdkman-init.sh && \
             sdk install java ${JAVA_VERSION} && \
             sdk default java ${JAVA_VERSION} && \
             sdk install maven ${MAVEN_VERSION} && \
             sdk default maven ${MAVEN_VERSION} && \
             sdk flush archives && sdk flush temp"

# 自动查找 jemalloc 动态库路径并软链接为 /usr/lib/libjemalloc.so.2
RUN jemalloc_path=$(find /usr/lib -name "libjemalloc.so.*" | grep -E "libjemalloc.so.[0-9]+(\.[0-9]+)*$" | head -n 1) && \
    ln -sf "$jemalloc_path" /usr/lib/libjemalloc.so.2

# 设置 jemalloc preload（适配任意架构）
ENV LD_PRELOAD="/usr/lib/libjemalloc.so.2"

# 启用 bash shell 以确保 sdkman 可用
SHELL ["/bin/bash", "-c"]

# 设置工作目录
WORKDIR /app

# 暴露 GDB 调试端口（native-image gdbserver 可用）
EXPOSE 5005

# 挂载 Maven 缓存
VOLUME ["/root/.m2"]

# 默认入口点（运行时可以覆盖）
ENTRYPOINT ["/bin/bash"]
