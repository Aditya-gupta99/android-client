package com.mifos.mifosxdroid.online.collectionsheetindividual

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.navigation.fragment.findNavController
import com.mifos.core.common.utils.Constants
import com.mifos.core.objects.collectionsheet.IndividualCollectionSheet
import com.mifos.feature.individual_collection_sheet.new_individual_collection_sheet.ui.NewIndividualCollectionSheetScreen
import com.mifos.mifosxdroid.core.MifosBaseFragment
import com.mifos.mifosxdroid.dialogfragments.collectionsheetdialog.CollectionSheetDialogFragment
import com.mifos.utils.FragmentConstants
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Locale


/**
 * Created by aksh on 18/6/18.
 */
@AndroidEntryPoint
class NewIndividualCollectionSheetFragment : MifosBaseFragment() {

    private var sheet: IndividualCollectionSheet? = null
    private lateinit var repaymentDate: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            sheet =
                savedInstanceState[Constants.EXTRA_COLLECTION_INDIVIDUAL] as IndividualCollectionSheet
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(
                ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed
            )
            setContent {
//                NewIndividualCollectionSheetScreen(popupDialog = {
//                    popupDialog(it)
//                }, repaymentDate = {
//                    repaymentDate = it
//                })
            }
        }
    }





//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        outState.putParcelable(
//            Constants.EXTRA_COLLECTION_INDIVIDUAL,
//            sheet
//        )
//    }
}