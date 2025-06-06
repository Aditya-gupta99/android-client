/*
 * Copyright 2024 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See https://github.com/openMF/android-client/blob/master/LICENSE.md
 */
@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.mifos.feature.client.clientChargeDialog

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mifos.core.designsystem.component.MifosCircularProgress
import com.mifos.core.designsystem.component.MifosDatePickerTextField
import com.mifos.core.designsystem.component.MifosOutlinedTextField
import com.mifos.core.designsystem.component.MifosSweetError
import com.mifos.core.designsystem.component.MifosTextFieldDropdown
import com.mifos.core.designsystem.icon.MifosIcons
import com.mifos.core.designsystem.theme.BluePrimary
import com.mifos.core.designsystem.theme.White
import com.mifos.core.objects.template.client.ChargeTemplate
import com.mifos.core.payloads.ChargesPayload
import com.mifos.feature.client.R
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
internal fun ChargeDialogScreen(
    clientId: Int,
    onDismiss: () -> Unit,
    onCreated: () -> Unit,
    viewModel: ChargeDialogViewModel = hiltViewModel(),
) {
    val state by viewModel.chargeDialogUiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.loadAllChargesV2(clientId)
    }

    ChargeDialogScreen(
        state = state,
        onDismiss = onDismiss,
        onCreate = { payload ->
            viewModel.createCharges(clientId, payload)
        },
        onCreated = onCreated,
    )
}

@Composable
internal fun ChargeDialogScreen(
    state: ChargeDialogUiState,
    onDismiss: () -> Unit,
    onCreate: (ChargesPayload) -> Unit,
    onCreated: () -> Unit,
) {
    var amount by rememberSaveable { mutableStateOf("") }
    var amountError by rememberSaveable { mutableStateOf(false) }
    val locale by rememberSaveable { mutableStateOf("en") }
    var dueDate by rememberSaveable { mutableLongStateOf(System.currentTimeMillis()) }
    val dueDatePickerState = rememberDatePickerState(
        initialSelectedDateMillis = dueDate,
        selectableDates = object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                return utcTimeMillis >= System.currentTimeMillis()
            }
        },
    )
    var showDatePicker by rememberSaveable { mutableStateOf(false) }

    fun validateInput(): Boolean {
        if (amount.isEmpty()) {
            amountError = true
            return false
        }
        return true
    }

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = {
                showDatePicker = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDatePicker = false
                        dueDatePickerState.selectedDateMillis?.let {
                            dueDate = it
                        }
                    },
                ) { Text(stringResource(id = R.string.feature_client_charge_select)) }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDatePicker = false
                    },
                ) { Text(stringResource(id = R.string.feature_client_charge_cancel)) }
            },
        ) {
            DatePicker(state = dueDatePickerState)
        }
    }

    Dialog(
        onDismissRequest = { onDismiss() },
    ) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White,
        ) {
            Box(
                contentAlignment = Alignment.Center,
            ) {
                when (state) {
                    is ChargeDialogUiState.AllChargesV2 -> {
                        var name by rememberSaveable { mutableStateOf(state.chargeTemplate.chargeOptions.first().name) }
                        var chargeId by rememberSaveable { mutableIntStateOf(state.chargeTemplate.chargeOptions.first().id) }

                        Column(modifier = Modifier.padding(20.dp)) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 16.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Text(
                                    text = stringResource(id = R.string.feature_client_charge_dialog),
                                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                                    color = BluePrimary,
                                )
                                IconButton(onClick = { onDismiss() }) {
                                    Icon(
                                        imageVector = MifosIcons.close,
                                        contentDescription = "",
                                        tint = colorResource(android.R.color.darker_gray),
                                        modifier = Modifier
                                            .width(30.dp)
                                            .height(30.dp),
                                    )
                                }
                            }

                            MifosTextFieldDropdown(
                                value = name,
                                onValueChanged = { value ->
                                    name = value
                                },
                                label = R.string.feature_client_charge_name,
                                readOnly = true,
                                onOptionSelected = { index, value ->
                                    chargeId = state.chargeTemplate.chargeOptions[index].id
                                    name = value
                                },
                                options = state.chargeTemplate.chargeOptions.map { it.name },
                            )

                            MifosOutlinedTextField(
                                value = amount,
                                onValueChange = { value ->
                                    amount = value
                                    amountError = false
                                },
                                label = stringResource(id = R.string.feature_client_charge_amount),
                                error = if (amountError) R.string.feature_client_message_field_required else null,
                                trailingIcon = {
                                    if (amountError) {
                                        Icon(
                                            imageVector = MifosIcons.error,
                                            contentDescription = null,
                                        )
                                    }
                                },
                            )

                            MifosDatePickerTextField(
                                value = SimpleDateFormat(
                                    "dd MMMM yyyy",
                                    Locale.getDefault(),
                                ).format(
                                    dueDate,
                                ),
                                label = R.string.feature_client_due_date,
                                openDatePicker = {
                                    showDatePicker = true
                                },
                            )

                            MifosOutlinedTextField(
                                value = locale,
                                onValueChange = {},
                                label = stringResource(id = R.string.feature_client_charge_locale),
                                error = null,
                                readOnly = true,
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            Button(
                                onClick = {
                                    if (validateInput()) {
                                        val payload = ChargesPayload().apply {
                                            this.amount = amount
                                            this.locale = locale
                                            this.dateFormat = "dd MMMM yyyy"
                                            this.chargeId = chargeId
                                            this.dueDate = SimpleDateFormat(
                                                "dd MMMM yyyy",
                                                Locale.getDefault(),
                                            ).format(
                                                dueDate,
                                            )
                                        }
                                        onCreate(payload)
                                    }
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(50.dp),
                                colors = ButtonColors(
                                    containerColor = BluePrimary,
                                    contentColor = White,
                                    disabledContainerColor = BluePrimary,
                                    disabledContentColor = Gray,
                                ),
                            ) {
                                Text(text = stringResource(id = R.string.feature_client_charge_submit))
                            }
                        }
                    }

                    is ChargeDialogUiState.Error -> MifosSweetError(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp),
                        message = stringResource(id = state.message),
                    ) {
                    }

                    is ChargeDialogUiState.Loading -> MifosCircularProgress(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(300.dp),
                    )

                    is ChargeDialogUiState.ChargesCreatedSuccessfully -> {
                        Toast.makeText(
                            LocalContext.current,
                            stringResource(id = R.string.feature_client_charge_created_successfully),
                            Toast.LENGTH_SHORT,
                        ).show()
                        onCreated()
                    }
                }
            }
        }
    }
}

private class ChargeDialogScreenUiStateProvider : PreviewParameterProvider<ChargeDialogUiState> {

    override val values: Sequence<ChargeDialogUiState>
        get() = sequenceOf(
            ChargeDialogUiState.AllChargesV2(ChargeTemplate(false, emptyList())),
            ChargeDialogUiState.Error(R.string.feature_client_failed_to_load_charges),
            ChargeDialogUiState.Loading,
            ChargeDialogUiState.ChargesCreatedSuccessfully,
        )
}

@Preview(showBackground = true)
@Composable
private fun ChargeDialogScreenPreview(
    @PreviewParameter(ChargeDialogScreenUiStateProvider::class) state: ChargeDialogUiState,
) {
    ChargeDialogScreen(
        state = state,
        onDismiss = {},
        onCreate = {},
        onCreated = {},
    )
}
