package com.github.jamsilva.ptgov_rootskip

import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage

const val ROOTCHECK_CHECKTASK_CLASS: String = "com.accenture.rootcheck.util.CheckTask"

class ModuleMain : IXposedHookLoadPackage {
    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        XposedHelpers.findAndHookMethod(
            ROOTCHECK_CHECKTASK_CLASS,
            lpparam.classLoader,
            "runCheck",
            object: XC_MethodHook() {
                override fun afterHookedMethod(param: MethodHookParam?) {
                    XposedHelpers.setBooleanField(param?.thisObject, "mIsFoundRoot", false)
                }
            }
        )
    }
}
