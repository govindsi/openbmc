# This recipe requires online access to build, as it uses NPM for dependency
# management and resolution.
PR = "r1"
PV = "1.0+git${SRCPV}"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e3fc50a88d0a364313df4b21ef20c29e"

SRC_URI = "git://github.com/openbmc/phosphor-webui.git;branch=master;protocol=https"
SRCREV = "c8ce0ace976c1a849166a4e61fd195fc3bf22b39"
S = "${WORKDIR}/git"

DEPENDS:prepend = "nodejs-native "

inherit allarch

FILES:${PN} += "${datadir}/www/*"

do_compile () {
    bbwarn "phosphor-webui is deprecated and has been replaced with webui-vue"
    cd ${S}
    rm -rf node_modules
    npm --loglevel info --proxy=${http_proxy} --https-proxy=${https_proxy} install
    npm run-script build
}

do_install () {
   # create directory structure
   install -d ${D}${datadir}/www
   cp -r ${S}/dist/** ${D}${datadir}/www
   find ${D}${datadir}/www -type f -exec chmod a=r,u+w '{}' +
   find ${D}${datadir}/www -type d -exec chmod a=rx,u+w '{}' +
}

