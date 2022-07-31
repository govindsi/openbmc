FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"
python () {
       d.setVar("KERNEL_DANGLING_FEATURES_WARN_ONLY","1")
}
