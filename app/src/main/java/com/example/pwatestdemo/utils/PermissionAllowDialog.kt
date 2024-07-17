package com.example.pwatestdemo.utils

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.example.pwatestdemo.R
import com.example.pwatestdemo.databinding.AllowPermissionBinding

class PermissionAllowDialog : DialogFragment() {
    internal var context: Context? = null
    lateinit var binding: AllowPermissionBinding
    lateinit var permissionType: String
    private var permissionListener: PermissionListener? = null
    private var closeListener: PermissionCloseListener? = null

    fun setPermissionListener(permissionListener: PermissionListener) {
        this.permissionListener = permissionListener
    }
    fun setPermissionCLoseListener(closeListener: PermissionCloseListener) {
        this.closeListener = closeListener
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.context = context
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val window = dialog?.window
        window?.apply {
            setBackgroundDrawableResource(android.R.color.transparent)
            setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            setGravity(Gravity.BOTTOM)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.allow_permission, container, false)
        val dialog = binding.root
        getDialog()?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        getDialog()?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        getDialog()?.setCanceledOnTouchOutside(false)
        getDialog()?.setCancelable(false)
        binding.lifecycleOwner = this
        permissionType = arguments?.getString("permissionType").toString()
        binding.permissionGrant.setOnClickListener {
            dismiss()
            permissionListener?.permissionGrant(permissionType)
        }
        binding.permissionTitle.text = PermissionUtil.permissionTitle(permissionType)
        binding.permissionDesc.text = PermissionUtil.permissionDesc(permissionType)
        binding.permissionIcon.setImageResource(PermissionUtil.permissionIcon(permissionType))
        binding.close.setOnClickListener {
            dismiss()
            if (closeListener == null) {
                dismiss()
            } else {
                closeListener?.onCLose(permissionType)
            }
        }

        return dialog
    }

    interface PermissionListener {
        fun permissionGrant(type: String)
    }
    interface PermissionCloseListener {
        fun onCLose(type: String)
    }
}
