package com.androidvoyage.zakat.feature_nisab.presentation.add_nisab

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.androidvoyage.zakat.R
import com.androidvoyage.zakat.ZakatApp
import com.androidvoyage.zakat.feature_nisab.presentation.add_nisab.components.BottomSheetNisabType
import com.androidvoyage.zakat.feature_nisab.presentation.add_nisab.components.TransparentHintTextField
import com.androidvoyage.zakat.feature_nisab.presentation.util.Features
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddEditNisabScreen(
    navController: NavController,
    nisabId: Long,
    nisabType: String,
    viewModel: AddEditNisabViewModel = hiltViewModel()
) {
    val titleState = viewModel.nisabTitle.value
    val amountState = viewModel.nisabAmount.value
    val contentState = viewModel.nisabContent.value
    val nisabType = viewModel.nisabType.value

    val scaffoldState = rememberScaffoldState()
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is AddEditNisabViewModel.UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is AddEditNisabViewModel.UiEvent.SaveNisab -> {
                    navController.navigateUp()
                }
            }
        }
    }

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
            BottomSheetNisabType(nisabType) {
                viewModel.onEvent(AddEditNisabEvent.SelectedType(it))
                coroutineScope.launch {
                    bottomSheetScaffoldState.bottomSheetState.collapse()
                }
            }
        }, sheetPeekHeight = 0.dp
    ) {
        BoxWithConstraints(modifier = Modifier
            .fillMaxSize()
            .clickable {
                coroutineScope.launch {
                    bottomSheetScaffoldState.bottomSheetState.collapse()
                }
            }) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = Color.Gray,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .clickable {
                            coroutineScope.launch {
                                if (bottomSheetScaffoldState.bottomSheetState.isCollapsed) {
                                    bottomSheetScaffoldState.bottomSheetState.expand()
                                } else {
                                    bottomSheetScaffoldState.bottomSheetState.collapse()
                                }
                            }
                        }
                        .padding(16.dp)
                ) {
                    Icon(
                        painter = painterResource(id = Features.getIcon(nisabType)),
                        contentDescription = "nisab type",
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .size(36.dp)
                            .padding(end = 16.dp)
                            .align(Alignment.CenterVertically)
                    )
                    Text(
                        text = nisabType.ifEmpty {
                            ZakatApp.getInstance()
                                .getString(R.string.str_hint_select_nisab)
                        },
                        style = MaterialTheme.typography.h2,
                        color = if (nisabType.isEmpty()) Color.LightGray else Color.Black,
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .align(Alignment.CenterVertically)

                    )
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Select Nisab Down",
                        modifier = Modifier
                            .wrapContentSize()
                            .align(Alignment.CenterVertically)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                TransparentHintTextField(
                    text = amountState.text,
                    hint = amountState.hint,
                    onValueChange = {
                        viewModel.onEvent(AddEditNisabEvent.EnteredAmount(it))
                    },
                    onFocusChange = {
                        viewModel.onEvent(AddEditNisabEvent.ChangeAmountFocus(it))
                    },
                    isHintVisible = amountState.isHintVisible,
                    singleLine = true,
                    textStyle = MaterialTheme.typography.h3,
                    drawableStart = R.drawable.ic_rupee,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = Color.Gray,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(16.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                TransparentHintTextField(
                    text = titleState.text,
                    hint = titleState.hint,
                    onValueChange = {
                        viewModel.onEvent(AddEditNisabEvent.EnteredTitle(it))
                    },
                    onFocusChange = {
                        viewModel.onEvent(AddEditNisabEvent.ChangeTitleFocus(it))
                    },
                    isHintVisible = titleState.isHintVisible,
                    singleLine = true,
                    textStyle = MaterialTheme.typography.h3,
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = Color.Gray,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(16.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                TransparentHintTextField(
                    text = contentState.text,
                    hint = contentState.hint,
                    onValueChange = {
                        viewModel.onEvent(AddEditNisabEvent.EnteredContent(it))
                    },
                    onFocusChange = {
                        viewModel.onEvent(AddEditNisabEvent.ChangeContentFocus(it))
                    },
                    isHintVisible = contentState.isHintVisible,
                    textStyle = MaterialTheme.typography.body2,
                    textAlignment = Alignment.Top,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.5f)
                        .border(
                            width = 0.5.dp,
                            color = Color.Gray,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(16.dp)
                )
            }
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(AddEditNisabEvent.SaveNisab)
                },
                backgroundColor = MaterialTheme.colors.primary,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .offset(-16.dp, -16.dp)
            ) {
                Icon(imageVector = Icons.Default.Save, contentDescription = "Save nisab")
            }
        }
    }
}
